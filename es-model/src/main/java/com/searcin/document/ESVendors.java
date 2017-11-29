package com.searcin.document;

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
							searchAnalyzer = "english",  indexAnalyzer = "english",
						    store = true, type = FieldType.String)
			})
	private String name;
		
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
	private List<String> logo;
	
	@JsonProperty("gallery")
	private List<String> gallery;

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

	public List<String> getLogo() {
		return logo;
	}

	public void setLogo(List<String> logo) {
		this.logo = logo;
	}

	public List<String> getGallery() {
		return gallery;
	}

	public void setGallery(List<String> gallery) {
		this.gallery = gallery;
	}
	
}
