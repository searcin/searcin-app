package com.searcin.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timings")
public class Timings {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "start_at")
	private Time startAt;
	
	@Column(name = "end_at")
	private Time endAt;

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

	public Time getStartAt() {
		return startAt;
	}

	public void setStartAt(Time startAt) {
		this.startAt = startAt;
	}

	public Time getEndAt() {
		return endAt;
	}

	public void setEndAt(Time endAt) {
		this.endAt = endAt;
	}
	
	
}
