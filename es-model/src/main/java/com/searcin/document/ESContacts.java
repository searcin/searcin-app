package com.searcin.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "#{ESConfig.indexName}", type = "contacts")
public class ESContacts {
	
	@Id
	private Integer id;
		
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
