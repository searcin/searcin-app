package com.searcin.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "#{ESConfig.indexName}", type = "contacts")
public class ESContacts {
	
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
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("mobile")
	private String mobile;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("website")
	private String website;
	
	@JsonProperty("facebook")
	private String facebook;
	
	@JsonProperty("twitter")
	private String twitter;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	
}
