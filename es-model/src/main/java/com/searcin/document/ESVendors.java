package com.searcin.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "#{ESConfig.indexName}", type = "vendors")
public class ESVendors {	
	@Id
	private Integer id;

	@JsonProperty("name")
	@MultiField(
			mainField = @Field(type=FieldType.String, store = true, analyzer = "case_insensitive"),
			otherFields = {
					@InnerField(suffix="key", index = FieldIndex.analyzed, 
							searchAnalyzer = "standard",  indexAnalyzer = "standard",
						    store = true, type = FieldType.String)
			})
	private String name;
	
	@JsonProperty("owner_name")
	private String ownerName;
		
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("category")
	private ESNested category;
	
	@JsonProperty("sub_category")
	private ESNested subCategory;
	
	@JsonProperty("classRange")
	private ESNested classRange;
	
	@JsonProperty("services")
	private List<ESNested> services;
	
	@JsonProperty("address")
	private ESAddresses address;
	
	@JsonProperty("contact")
	private ESContacts contact;
	
	@JsonProperty("logo")
	private String logo;
	
	@JsonProperty("gallery")
	private List<String> gallery;
	
	@JsonProperty("timings")
	private List<ESTimings> timings;
	
	@JsonProperty("created_on")
	private Date createdOn;

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
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ESNested getCategory() {
		return category;
	}

	public void setCategory(ESNested category) {
		this.category = category;
	}

	public ESNested getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(ESNested subCategory) {
		this.subCategory = subCategory;
	}

	public ESNested getClassRange() {
		return classRange;
	}

	public void setClassRange(ESNested classRange) {
		this.classRange = classRange;
	}

	public List<ESNested> getServices() {
		return services;
	}

	public void setServices(List<ESNested> services) {
		this.services = services;
	}

	public ESAddresses getAddress() {
		return address;
	}

	public void setAddress(ESAddresses address) {
		this.address = address;
	}

	public ESContacts getContact() {
		return contact;
	}

	public void setContact(ESContacts contact) {
		this.contact = contact;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<String> getGallery() {
		return gallery;
	}

	public void setGallery(List<String> gallery) {
		this.gallery = gallery;
	}

	public List<ESTimings> getTimings() {
		return timings;
	}

	public void setTimings(List<ESTimings> timings) {
		this.timings = timings;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}	
}
