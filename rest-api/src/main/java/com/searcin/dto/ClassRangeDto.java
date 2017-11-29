package com.searcin.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class ClassRangeDto {
	@JsonView(Dto.ClassRange.id.class)
	private Integer id;
	
	@JsonView(Dto.ClassRange.name.class)
	private String name;
	
	public ClassRangeDto() {
		
	}

	public ClassRangeDto(Integer id, String name) {
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

}
