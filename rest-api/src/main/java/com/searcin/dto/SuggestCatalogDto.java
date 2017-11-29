package com.searcin.dto;

import java.util.List;

public class SuggestCatalogDto {
	private Integer id;
	private String key;
	private String type;
	private List<String> logo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getLogo() {
		return logo;
	}

	public void setLogo(List<String> logo) {
		this.logo = logo;
	}

}
