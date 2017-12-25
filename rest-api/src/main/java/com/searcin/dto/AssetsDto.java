package com.searcin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class AssetsDto {
	@JsonView(Dto.Asset.id.class)
	private Integer id;

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

	public AssetsDto(Integer id, String url, String updatedBy, Date updatedOn) {
		this.id = id;
		this.url = url;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
