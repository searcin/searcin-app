package com.searcin.service.impl;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.searcin.exception.AwsS3Exception;
import com.searcin.service.S3BucketService;

@Service
public class S3BucketServiceImpl implements S3BucketService {

	private final Logger LOGGER = LoggerFactory.getLogger(S3BucketServiceImpl.class);

	@Autowired
	private AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.region}")
	private String region;

	@Override
	public String getHost() {
		return "https://s3." + region + ".amazonaws.com/" + bucket + "/";
	}

	@PostConstruct
	public void test() {

		list("vendor").forEach(item -> {
			DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, item.getKey());
			amazonS3Client.deleteObject(deleteObjectRequest);
		});
	}

	@Override
	public List<S3ObjectSummary> list(String prefix) {
		return amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(bucket).withPrefix(prefix))
				.getObjectSummaries().stream().sorted(Comparator.comparing(S3ObjectSummary::getLastModified))
				.collect(Collectors.toList());

	}

	@Override
	public void upload(MultipartFile file, String path) throws IOException {
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, path, file.getInputStream(), null);
		putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
		amazonS3Client.putObject(putObjectRequest);
	}

	@Override
	public void delete(String key) {
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, key);
		amazonS3Client.deleteObject(deleteObjectRequest);
	}

	@PostConstruct
	public void isBucketAvailable() {
		try {
			LOGGER.info("AWS S3 bucket {} availability {}", bucket, amazonS3Client.doesBucketExist(bucket));
		} catch (Exception e) {
			LOGGER.error("aws s3 bucket exception {}", e);
			throw new AwsS3Exception("Image server connectivity issue!");
		}
	}
}
