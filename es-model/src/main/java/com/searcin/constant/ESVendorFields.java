package com.searcin.constant;

public enum ESVendorFields {
	ID("id"),NAME("name"),DESCRIPTION("description"),CATEGORY("category"),SUBCATEGORY("subCategory"),
	SERVICES("services"),CONTACT("contact"),ADDRESS("address"),LOGO("logo"),GALLERY("gallery");
	
	private String field;
	
	ESVendorFields(String field) {
		this.field = field;
	}
	
	public String getField() {
		return this.field;
	}
	
}
