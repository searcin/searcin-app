package com.searcin.constant;

public enum ImageType {
	JPG("image/jpeg"), PNG("image/png");
	
	private String value;
	
	ImageType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
