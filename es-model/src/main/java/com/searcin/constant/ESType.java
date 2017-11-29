package com.searcin.constant;

public enum ESType {
	AREAS("areas"),ADDRESSES("addresses"),CATEGORIES("categories"),CONTACTS("contacts"),SERVICES("services"),
	SUBCATEGORIES("subcategories"),VENDORS("vendors");
	
	private String typeName;
	
	ESType(String typeName) {
		this.typeName = typeName;
	}
	
	public String getName() {
		return this.typeName;
	}
}
