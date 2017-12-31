package com.searcin.constant;

public enum AssetType {
	VENDORGALLERY("vendor_gallery", 5),VENDORLOGO("vendor_logo", 1);
	
	private String value;
	private Integer maxCount;
	
	AssetType(String value, Integer maxCount) {
		this.value = value;
		this.maxCount = maxCount;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public Integer getMaxCount() {
		return this.maxCount;
	}
}
