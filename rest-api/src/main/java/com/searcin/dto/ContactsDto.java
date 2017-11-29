package com.searcin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class ContactsDto {
	@JsonView(Dto.Contact.id.class)
	private Integer id;
	
	@JsonView(Dto.Contact.name.class)
	private String name;
	
	@JsonView(Dto.Contact.detail.class)
	private String phone;
	
	@JsonView(Dto.Contact.basic.class)
	private String mobile;
	
	@JsonView(Dto.Contact.basic.class)
	private String email;
	
	@JsonView(Dto.Contact.detail.class)
	private String website;
	
	@JsonView(Dto.Contact.detail.class)
	private String facebook;
	
	@JsonView(Dto.Contact.detail.class)
	private String twitter;
	
	@JsonView(Dto.Contact.audit.class)
	private Date updatedOn;
	
	@JsonView(Dto.Contact.audit.class)
	private String updatedBy;
	
	public ContactsDto() {
		
	}
	
	public ContactsDto(String name, String phone, String mobile, String email, String website, 
			String facebook, String twitter) {
		this.name = name;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.website = website;
		this.facebook = facebook;
		this.twitter = twitter;
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
