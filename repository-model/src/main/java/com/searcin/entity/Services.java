package com.searcin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.searcin.listener.ServicesListener;

@Entity
@EntityListeners(ServicesListener.class)
@Table(name = "services")
public class Services extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_active", insertable = false)
	private Boolean isActive;

	public Services() {

	}

	public Services(Integer service_id) {
		this.id = service_id;
	}

	public Services(Integer service_id, String service_name) {
		this.id = service_id;
		this.name = service_name;
	}

	public Services(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Services [id=" + id + ", name=" + name + ", description=" + description + ", isActive=" + isActive
				+ "]";
	}

}
