package com.searcin.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searcin.constant.AssetType;
import com.searcin.entity.VendorAsset;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class VendorAssetListener {
	
	private final Logger log = LoggerFactory.getLogger(VendorAssetListener.class);
	
	@PostPersist
	@PostUpdate
	public void save(VendorAsset vendorAsset) {
		if(vendorAsset.getAssetType().equals(AssetType.LOGO.getValue())) {
			BeanUtil.getBean(IngestionService.class).saveLogo(vendorAsset.getMetadata(), vendorAsset.getVendor().getId());
		}
		else if(vendorAsset.getAssetType().equals(AssetType.GALLERY.getValue())) {
			BeanUtil.getBean(IngestionService.class).saveGallery(vendorAsset.getMetadata(), vendorAsset.getVendor().getId());
		}
		else {
			log.error("Unknow asset type!");
		}
	}
	
	@PostRemove
	public void delete(VendorAsset vendorAsset) {
		if(vendorAsset.getAssetType().equals(AssetType.LOGO.getValue())) {
			BeanUtil.getBean(IngestionService.class).deleteLogo(vendorAsset.getVendor().getId());
		}
		else if(vendorAsset.getAssetType().equals(AssetType.GALLERY.getValue())) {
			BeanUtil.getBean(IngestionService.class).deleteGallery(vendorAsset.getMetadata(), vendorAsset.getVendor().getId());
		}
		else {
			log.error("Unknow asset type!");
		}
	}
}
