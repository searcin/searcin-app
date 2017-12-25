package com.searcin.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import com.searcin.listener.VendorsListener;

@Entity
@EntityListeners(VendorsListener.class)
@DynamicUpdate
@Table(name = "vendors")
public class Vendors extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Access(AccessType.PROPERTY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "owner_name")
	private String ownerName;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Categories category;

	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategories subCategory;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "vendor_address", joinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"), 
				inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
	private Addresses address;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "vendor_contact", joinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"), 
				inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"))
	private Contacts contact;

	@OneToOne
	@JoinColumn(name = "class_range_id")
	private ClassRange classRange;

	@ManyToMany
	@JoinTable(name = "vendor_services", joinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"), 
				inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
	private List<Services> services;

	@OneToMany(mappedBy="vendor", orphanRemoval = true)
	private List<VendorAsset> assets;

	@Column(name = "is_active")
	private Boolean isActive;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", updatable = false)
	private Date createdOn;

	public Vendors() {

	}

	public Vendors(Integer id) {
		this.id = id;
	}

	public Vendors(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Vendors(Integer id, String name, String description, Categories category, SubCategories subCategory,
			ClassRange classRange, Collection<Services> services) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.subCategory = subCategory;
		this.classRange = classRange;
		this.services = services.stream().collect(Collectors.toList());
	}

	public Vendors(Integer id, Addresses address) {
		this.id = id;
		this.address = address;
	}

	public Vendors(Integer id, Contacts contact) {
		this.id = id;
		this.contact = contact;
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

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public List<VendorAsset> getAssets() {
		return assets;
	}

	public void setAssets(List<VendorAsset> assets) {
		this.assets = assets;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public SubCategories getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategories subCategory) {
		this.subCategory = subCategory;
	}

	public Addresses getAddress() {
		return address;
	}

	public void setAddress(Addresses address) {
		this.address = address;
	}

	public Contacts getContact() {
		return contact;
	}

	public void setContact(Contacts contact) {
		this.contact = contact;
	}

	public List<Services> getServices() {
		return services;
	}

	public void setServices(List<Services> services) {
		this.services = services;
	}

	public ClassRange getClassRange() {
		return classRange;
	}

	public void setClassRange(ClassRange classRange) {
		this.classRange = classRange;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
