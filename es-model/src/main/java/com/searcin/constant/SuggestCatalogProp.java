package com.searcin.constant;

public enum SuggestCatalogProp {
	ID("id"),KEY("key"),TYPE("type"),LOGO("logo");
	
	private String property;
	
	SuggestCatalogProp(String property) {
		this.property = property;
	}
	
	public String getProperty() {
		return this.property;
	}
}
