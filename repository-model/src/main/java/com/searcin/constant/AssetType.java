package com.searcin.constant;

public enum AssetType {
	GALLERY("gallery"),LOGO("logo");
	
	private String value;
	
	AssetType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
