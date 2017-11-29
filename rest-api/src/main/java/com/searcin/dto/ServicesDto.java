package com.searcin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class ServicesDto {
	
	@JsonView(Dto.Service.id.class)
	private Integer id;
	
	@JsonView(Dto.Service.name.class)
	private String name;
	
	@JsonView(Dto.Service.desc.class)
	private String description;
	
	@JsonView(Dto.Service.audit.class)
	private Date updatedOn;
	
	@JsonView(Dto.Service.audit.class)
	private String updatedBy;
	
	public ServicesDto() {
		
	}
	
	public ServicesDto(Integer id, String name) {
		this.id = id;
		this.name = name;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
