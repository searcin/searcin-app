package com.searcin.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.springframework.data.annotation.CreatedDate;

@Entity
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

	@Column(name = "owner_name")
	private String ownerName;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Categories category;

	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategories subCategory;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "vendor_address", joinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
	private Addresses address;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "vendor_contact", joinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"))
	private Contacts contact;

	@OneToOne
	@JoinColumn(name = "class_range_id")
	private ClassRange classRange;

	@ManyToMany
	@JoinTable(name = "vendor_services", joinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
	private List<Services> services;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "vendor_asset", joinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "asset_id", referencedColumnName = "id"))
	private List<Assets> assets;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "vendor_timing", joinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "timing_id", referencedColumnName = "id"))
	private List<Timings> timing;

	@Column(name = "is_active")
	private Boolean isActive = true;

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

	public List<Assets> getAssets() {
		return assets;
	}

	public void setAssets(List<Assets> assets) {
		this.assets = assets;
	}
	
	public void mergeAssets(Assets asset) {
		this.assets.add(asset);
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

	public List<Timings> getTiming() {
		return timing;
	}
	
	public void setTiming(List<Timings> timing) {
		this.timing = timing;
	}

	public void mergeTiming(List<Timings> timing) {
		if (this.timing.size() > 0) {			
			Integer index = 0;
			for (Timings existing : this.timing) {
				for (Timings changed : timing) {
					if (existing.getId().equals(changed.getId())) {
						if (!Optional.ofNullable(changed.getStartAt()).map(item -> item.getTime())
								.orElse(0L).equals(Optional.ofNullable(existing.getStartAt()).map(item -> item.getTime())
										.orElse(0L)) || !Optional.ofNullable(changed.getEndAt()).map(item -> item.getTime())
								.orElse(0L).equals(Optional.ofNullable(existing.getEndAt()).map(item -> item.getTime())
										.orElse(0L))) {
							this.timing.set(index, changed);
						}
						break;
					}
				}
				index++;
			}
		} else {
			timing.forEach(this.timing::add);
		}
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
