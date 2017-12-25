package com.searcin.constant;

public enum RoleType {
	ADMIN(1),USER(2),VENDOR(3);
	
	private Integer id;
	
	RoleType(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
}
