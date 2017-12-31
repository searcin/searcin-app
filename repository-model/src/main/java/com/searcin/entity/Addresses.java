package com.searcin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Addresses extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "area_id")
	private Areas area;

	@Column(name = "door_no")
	private String doorno;

	@Column(name = "building")
	private String building;

	@Column(name = "nearby")
	private String nearby;

	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2")
	private String address2;

	@Column(name = "pincode")
	private Integer pincode;

	@Column(name = "lat")
	private Double lat;

	@Column(name = "lng")
	private Double lng;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Areas getArea() {
		return area;
	}

	public void setArea(Areas area) {
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

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
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

	@Override
	public String toString() {
		return "Addresses [id=" + id + ", area=" + area + ", doorno=" + doorno + ", building=" + building + ", nearby="
				+ nearby + ", address1=" + address1 + ", address2=" + address2 + ", pincode=" + pincode + ", lat=" + lat
				+ ", lng=" + lng + ", getUpdatedOn()=" + getUpdatedOn() + ", getUpdatedBy()=" + getUpdatedBy() + "]";
	}

	
}
