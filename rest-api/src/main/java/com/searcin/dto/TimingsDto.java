package com.searcin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class TimingsDto {
	
	@JsonView(Dto.Timings.id.class)
	private Integer id;
	
	@JsonView(Dto.Timings.day.class)
	private Integer day;
	
	@JsonView(Dto.Timings.label.class)
	private String label;
	
	@JsonView(Dto.Timings.detail.class)
	private String startAt;
	
	@JsonView(Dto.Timings.detail.class)
	private String endAt;
	
	@JsonView(Dto.Timings.audit.class)
	private Date updatedOn;
	
	@JsonView(Dto.Timings.audit.class)
	private String updatedBy;

	public TimingsDto() {

	}

	public TimingsDto(Integer day, String label) {
		this.day = day;
		this.label = label;
	}

	public TimingsDto(Integer day, String label, String startAt, String endAt) {
		this.day = day;
		this.label = label;
		this.startAt = startAt;
		this.endAt = endAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStartAt() {
		return startAt;
	}

	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}

	public String getEndAt() {
		return endAt;
	}

	public void setEndAt(String endAt) {
		this.endAt = endAt;
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
