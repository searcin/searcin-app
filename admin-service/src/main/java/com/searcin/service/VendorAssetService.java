package com.searcin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.searcin.entity.VendorAsset;

public interface VendorAssetService {
	void uploadLogo(Integer id, MultipartFile multipartFile);

	void uploadGallery(Integer id, MultipartFile multipartFile);

	VendorAsset getLogo(Integer id);

	List<VendorAsset> getGallery(Integer id);

	void deleteAssetByKey(Integer id, Integer assetId);

	void deleteAssetsByVendor(Integer id);
}
