package com.searcin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class AreasDto {

	@JsonView(Dto.Area.id.class)
	private Integer id;
	@JsonView(Dto.Area.name.class)
	private String name;
	@JsonView(Dto.Area.audit.class)
	private Date updatedOn;
	@JsonView(Dto.Area.audit.class)
	private String updatedBy;
	
	public AreasDto() {
		
	}
	
	public AreasDto(Integer id, String name) {
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
