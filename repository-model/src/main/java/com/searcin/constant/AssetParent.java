package com.searcin.constant;

public enum AssetParent {
	VENDOR("vendor");
	
	private String value;
	
	AssetParent(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
