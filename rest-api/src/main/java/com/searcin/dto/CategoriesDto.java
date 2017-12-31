package com.searcin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class CategoriesDto {
	
	@JsonView(Dto.Category.id.class)
	private Integer id;
	
	@JsonView(Dto.Category.name.class)
	private String name;
	
	@JsonView(Dto.Category.audit.class)
	private Date updatedOn;
	
	@JsonView(Dto.Category.audit.class)
	private String updatedBy;
	
	public CategoriesDto() {
		
	}
	
	public CategoriesDto(Integer id, String name) {
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

	@Override
	public String toString() {
		return "CategoriesDto [id=" + id + ", name=" + name + ", updatedOn=" + updatedOn + ", updatedBy=" + updatedBy
				+ "]";
	}
	
	
}
