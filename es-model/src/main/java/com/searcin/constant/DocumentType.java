package com.searcin.constant;

public enum DocumentType {
	AREAS("areas"), CATEGORIES("categories"), SERVICES("services"), SUBCATEGORIES("subcategories"), VENDORS("vendors");
	
	private String typeName;

	DocumentType(String typeName) {
		this.typeName = typeName;
	}

	public String getName() {
		return this.typeName;
	}
}
