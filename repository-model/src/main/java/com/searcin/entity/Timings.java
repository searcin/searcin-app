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
public class Timings extends Auditable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "week_day")
	private Integer day;
	
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

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
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
