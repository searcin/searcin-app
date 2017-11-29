package com.searcin.constant;

public enum VendorDetailProp {
	ID("id"), 
	NAME("name"), 
	DESCRIPTION("description"), 
	CATEGORY("category"), 
	CATEGORYID("category_id"), 
	SUBCATEGORY("sub_category"), 
	SUBCATEGORYID("sub_category_id"), 
	CLASSRANGE("class_range"), 
	CREATEDON("created_on"), 
	SERVICES("services"), 
	AREA("area"), 
	AREAID("area_id"), 
	DOORNO("door_no"), 
	BUILDING("building"), 
	NEARBY("nearby"), 
	ADDRESS1("address1"), 
	ADDRESS2("address2"), 
	PINCODE("pincode"), 
	LOCATION("location"), 
	LAT("lat"), 
	LON("lon"), 
	CONTACTNAME("contact_name"), 
	PHONE("phone"), 
	MOBILE("mobile"), 
	EMAIL("email"), 
	WEBSITE("website"), 
	FACEBOOK("facebook"), 
	TWITTER("twitter"), 
	LOGO("logo"), 
	GALLERY("gallery");

	private String property;

	VendorDetailProp(String property) {
		this.property = property;
	}

	public String getProperty() {
		return this.property;
	}
}

