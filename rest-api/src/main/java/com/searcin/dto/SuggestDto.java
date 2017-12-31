package com.searcin.dto;

public class SuggestDto {
	private Integer id;
	private String name;
	private String type;
	private String description;
	private String logo;

	public SuggestDto() {

	}

	public SuggestDto(Integer id, String name, String type, String description, String logo) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.logo = logo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
