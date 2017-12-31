package com.searcin.document;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "#{ESConfig.indexName}", type = "addresses")
public class ESAddresses {
	
	@Id
	private Integer id;
	
	@JsonProperty("area")
	private ESNested area;
	
	@JsonProperty("doorno")
	private String doorno;
	
	@JsonProperty("building")
	private String building;
	
	@JsonProperty("nearby")
	private String nearby;
	
	@JsonProperty("address1")
	private String address1;
	
	@JsonProperty("address2")
	private String address2;
	
	@JsonProperty("pincode")
	private Integer pincode;
	
	@JsonProperty("location")
	private Map<String, Double> location = new HashMap<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public ESNested getArea() {
		return area;
	}

	public void setArea(ESNested area) {
		this.area = area;
	}
	
	public void setArea(Integer id, String name) {
		this.area.setId(id);
		this.area.setName(name);
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

	public Map<String, Double> getLocation() {
		return location;
	}

	public void setLocation(Map<String, Double> location) {
		this.location = location;
	}

	public void setLat(Double lat) {
		this.location.put("lat", lat);
	}
	
	public void setLng(Double lng) {
		this.location.put("lon", lng);
	}

	@Override
	public String toString() {
		return "ESAddresses [id=" + id + ", area=" + area + ", doorno=" + doorno + ", building=" + building
				+ ", nearby=" + nearby + ", address1=" + address1 + ", address2=" + address2 + ", pincode=" + pincode
				+ ", location=" + location + "]";
	}
	
	
}
