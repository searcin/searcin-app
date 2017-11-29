package com.searcin.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.searcin.constant.AssetType;
import com.searcin.service.AssetsService;

@Service
public class AssetsServiceImpl implements AssetsService {
	
	private static final String uploadedBy = "alt-uploaded-by";

	@Autowired
	private AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.region}")
	private String region;

	@Value("${assets.vendor}")
	private String vendor;

	@Value("${assets.vendor.logo.limit}")
	private Integer logoLimit;

	@Value("${assets.vendor.gallery.limit}")
	private Integer galleryLimit;

	public boolean isBucketAvailable() {
		return amazonS3Client.doesBucketExist(bucket);
	}

	public boolean isValidAssetType(String type) {
		if (type.equals(AssetType.GALLERY.getValue())) {
			return true;
		} else if (type.equals(AssetType.LOGO.getValue())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isWithinLimit(Integer size, Integer id, String type) {

		Integer allowedSize;
		Integer existingSize = list(id, type).size();

		if (type.equals(AssetType.GALLERY.getValue())) {
			allowedSize = galleryLimit - (size + existingSize);
			if (allowedSize >= 0)
				return true;
			else
				return false;
		} else if (type.equals(AssetType.LOGO.getValue())) {
			allowedSize = logoLimit - (size + existingSize);
			if (allowedSize >= 0)
				return true;
			else
				return false;
		} else {
			return false;
		}

	}

	public String getHost() {
		return "https://s3." + region + ".amazonaws.com/" + bucket + "/";
	}
	
	public List<S3ObjectSummary> list(Integer id, String type) {
		String prefix = vendor + "/" + id + "/" + type;
		return amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(bucket)
				.withPrefix(prefix)).getObjectSummaries();		
	}

	public String getUploadedBy(S3ObjectSummary obj) {
		return amazonS3Client.getObjectMetadata(bucket, obj.getKey()).getUserMetadata().get(uploadedBy);
	}

	public void upload(MultipartFile[] multipartFiles, Integer id, String type, String username) {
		Arrays.stream(multipartFiles).forEach(multipartFile -> {
			try {
				upload(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), id, type, username);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void upload(InputStream inputStream, String uploadKey, Integer id, String type, String username) {
		String imgPath = vendor + "/" + id + "/" + type + "/" + uploadKey;
		ObjectMetadata obj = new ObjectMetadata();
		obj.addUserMetadata(uploadedBy, username);
		
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, imgPath, inputStream, obj);
		
		putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
		amazonS3Client.putObject(putObjectRequest);
		IOUtils.closeQuietly(inputStream);
	}

	public void delete(String key) {
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, key);
		amazonS3Client.deleteObject(deleteObjectRequest);
	}

	public void deleteAll(Integer id) {
		delete(vendor + "/" + id);
	}
}
