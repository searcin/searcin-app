package com.searcin.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.searcin.listener.VendorsListener;

@Entity
@EntityListeners(VendorsListener.class)
@Table(name = "vendors")
public class Vendors extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Categories category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_category_id")
	private SubCategories subCategory;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "vendor")
	private Addresses address;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "vendor")
	private Contacts contact;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_range_id")
	private ClassRange classRange;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "vendor_services", joinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
	private List<Services> services;

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

}
