package com.searcin.constant;

public enum ESSuggest {
	VENDORS(5),SERVICES(4),CATEGORIES(3),SUBCATEGORIES(2),AREAS(1);
	
	private Integer boost;
	
	ESSuggest(Integer boost) {
		this.boost = boost;
	}
	
	public Integer getBoost() {
		return this.boost;
	}
}
