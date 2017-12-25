package com.searcin.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.searcin.constant.AssetType;
import com.searcin.entity.VendorAsset;
import com.searcin.entity.Vendors;
import com.searcin.exception.AwsS3Exception;
import com.searcin.exception.ObjectNotFoundException;
import com.searcin.exception.OutOfLimitException;
import com.searcin.repository.VendorAssetRepository;
import com.searcin.service.S3BucketService;
import com.searcin.service.VendorAssetService;

@Service
public class VendorAssetServiceImpl implements VendorAssetService {

	private final Logger log = LoggerFactory.getLogger(VendorAssetServiceImpl.class);

	@Value("${assets.environment}")
	private String environment;

	@Value("${assets.vendor}")
	private String vendor;

	@Value("${assets.vendor.gallery.limit}")
	private Integer galleryLimit;

	@Autowired
	private S3BucketService s3BucketService;

	@Autowired
	private VendorAssetRepository vendorAssetRepository;

	@Override
	public void uploadLogo(Integer id, MultipartFile file) {
		String assetType = AssetType.LOGO.getValue();
		String path = environment + "/" + vendor + "/" + id + "/" + assetType + "/" + file.getOriginalFilename();
		if (vendorAssetRepository.countByVendor(id, assetType).equals(0)) {
			uploadToS3Bucket(file, path, id, assetType);
		} else {
			throw new OutOfLimitException("Logo already available for the vendor!");
		}
	}

	@Override
	public void uploadGallery(Integer id, MultipartFile file) {
		String assetType = AssetType.GALLERY.getValue();
		if (vendorAssetRepository.countByVendor(id, assetType) < galleryLimit) {
			String path = environment + "/" + vendor + "/" + id + "/" + assetType + "/" + file.getOriginalFilename();
			uploadToS3Bucket(file, path, id, assetType);
		} else {
			throw new OutOfLimitException("Gallery limit exceeded!");
		}
	}

	@Override
	public VendorAsset getLogo(Integer id) {
		List<VendorAsset> vendorAssets = vendorAssetRepository.findByVendor(id, AssetType.LOGO.getValue());
		if (vendorAssets.size() > 0)
			return vendorAssets.get(0);
		else
			return null;
	}

	@Override
	public List<VendorAsset> getGallery(Integer id) {
		return vendorAssetRepository.findByVendor(id, AssetType.GALLERY.getValue());
	}

	@Override
	public void deleteAssetByKey(Integer id, Integer assetId) {
		vendorAssetRepository.delete(Optional.ofNullable(vendorAssetRepository.findById(assetId)).map(item -> {
			s3BucketService.delete(item.getAssetKey());
			return item;
		}).orElseThrow(() -> new ObjectNotFoundException("Asset not found!")));
	}

	@Override
	public void deleteAssetsByVendor(Integer id) {
		vendorAssetRepository.findByVendor_id(id).forEach(item -> {
			// delete from aws bucket
			s3BucketService.delete(item.getAssetKey());
			// delete from database
			vendorAssetRepository.delete(item);
		});
	}

	private void uploadToS3Bucket(MultipartFile file, String path, Integer id, String assetType) {
		try {
			log.info("Uploading to s3 bucket...");
			s3BucketService.upload(file, path);
			log.info("Saving to database...");
			vendorAssetRepository
					.save(new VendorAsset(new Vendors(id), assetType, path, s3BucketService.getHost() + path));
		} catch (IOException e) {
			throw new AwsS3Exception("IO Exception {}", e);
		}
	}

}
