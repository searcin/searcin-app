package com.searcin.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class VendorsDto {

	@JsonView(Dto.Vendor.id.class)
	private Integer id;

	@JsonView(Dto.Vendor.name.class)
	private String name;

	@JsonView(Dto.Vendor.description.class)
	private String description;

	@JsonView(Dto.Vendor.hierarchy.class)
	private CategoriesDto category;

	@JsonView(Dto.Vendor.hierarchy.class)
	private SubCategoriesDto subCategory;

	@JsonView(Dto.Vendor.classrange.class)
	private ClassRangeDto classRange;

	@JsonView(Dto.Vendor.services.class)
	private List<ServicesDto> services;

	@JsonView(Dto.Vendor.address.class)
	private AddressesDto address;

	@JsonView(Dto.Vendor.contact.class)
	private ContactsDto contact;

	@JsonView(Dto.Vendor.logo.class)
	private List<AssetsDto> logo;

	@JsonView(Dto.Vendor.images.class)
	private List<AssetsDto> images;

	@JsonView(Dto.Vendor.audit.class)
	private Date updatedOn;

	@JsonView(Dto.Vendor.audit.class)
	private String updatedBy;

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

	public CategoriesDto getCategory() {
		return category;
	}

	public void setCategory(CategoriesDto category) {
		this.category = category;
	}

	public SubCategoriesDto getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategoriesDto subCategory) {
		this.subCategory = subCategory;
	}

	public ClassRangeDto getClassRange() {
		return classRange;
	}

	public void setClassRange(ClassRangeDto classRange) {
		this.classRange = classRange;
	}

	public List<ServicesDto> getServices() {
		return services;
	}

	public void setServices(List<ServicesDto> services) {
		this.services = services;
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

	public AddressesDto getAddress() {
		return address;
	}

	public void setAddress(AddressesDto address) {
		this.address = address;
	}

	public ContactsDto getContact() {
		return contact;
	}

	public void setContact(ContactsDto contact) {
		this.contact = contact;
	}

	public List<AssetsDto> getLogo() {
		return logo;
	}

	public void setLogo(List<AssetsDto> logo) {
		this.logo = logo;
	}

	public List<AssetsDto> getImages() {
		return images;
	}

	public void setImages(List<AssetsDto> images) {
		this.images = images;
	}

}
