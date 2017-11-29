package com.searcin.constant;

public enum SuggestType {
	CATEGORY(3.0f),SUBCATEGORY(2.0f),SERVICE(4.0f),VENDOR(5.0f),AREA(1.0f);
	
	private Float boost;
	
	SuggestType(Float boost) {
		this.boost = boost;
	}
	
	public Float getBoost() {
		return this.boost;
	}
}
