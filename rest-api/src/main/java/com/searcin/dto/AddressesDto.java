package com.searcin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class AddressesDto {
	
	@JsonView(Dto.Address.id.class)
	private Integer id;
	
	@JsonView(Dto.Address.name.class)
	private AreasDto area;
	
	@JsonView(Dto.Address.basic.class)
	private String doorno;
	
	@JsonView(Dto.Address.detail.class)
	private String building;
	
	@JsonView(Dto.Address.detail.class)
	private String nearby;
	
	@JsonView(Dto.Address.basic.class)
	private String address1;
	
	@JsonView(Dto.Address.basic.class)
	private String address2;
	
	@JsonView(Dto.Address.location.class)
	private Double lat;
	
	@JsonView(Dto.Address.location.class)
	private Double lng;
	
	@JsonView(Dto.Address.basic.class)
	private Integer pincode;
	
	@JsonView(Dto.Address.audit.class)
	private Date updatedOn;
	
	@JsonView(Dto.Address.audit.class)
	private String updatedBy;
	
	public AddressesDto() {
		
	}
	
	public AddressesDto(AreasDto area, String doorno, String building, String nearby, String address1, String address2,
			Double lat, Double lng, Integer pincode) {
		this.area = area;
		this.doorno = doorno;
		this.building = building;
		this.nearby = nearby;
		this.address1 = address1;
		this.address2 = address2;
		this.lat = lat;
		this.lng = lng;
		this.pincode = pincode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AreasDto getArea() {
		return area;
	}

	public void setArea(AreasDto area) {
		this.area = area;
	}

	public String getDoorno() {
		return doorno;
	}

	public void setDoorno(String doorno) {
		this.doorno = doorno;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getNearby() {
		return nearby;
	}

	public void setNearby(String nearby) {
		this.nearby = nearby;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
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
