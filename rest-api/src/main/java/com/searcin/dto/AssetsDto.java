package com.searcin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class AssetsDto {
	@JsonView(Dto.Asset.key.class)
	private String key;
	
	@JsonView(Dto.Asset.url.class)
	private String url;
	
	@JsonView(Dto.Asset.audit.class)
	private String updatedBy;
	
	@JsonView(Dto.Asset.audit.class)
	private Date updatedOn;
	
	public AssetsDto() {
		
	}
	
	public AssetsDto(String url) {
		this.url = url;
	}
	
	public AssetsDto(String key, String url, String updatedBy, Date updatedOn) {
		this.key = key;
		this.url = url;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
}
