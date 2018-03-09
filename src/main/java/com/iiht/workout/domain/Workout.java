package com.iiht.workout.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "workout")
public class Workout implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "workoutid", updatable = false, nullable = false)
	private long id;

	private String title;
	private double calBurntPerUnitTime;
	@Enumerated(EnumType.STRING)
	private UnitTime unitTime;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "userid")
	private User user;

	public Workout() {
	}

	public Workout(long id, String title, double calBurntPerUnitTime, UnitTime unitTime, User user) {
		this.id = id;
		this.title = title;
		this.calBurntPerUnitTime = calBurntPerUnitTime;
		this.unitTime = unitTime;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getCalBurntPerUnitTime() {
		return calBurntPerUnitTime;
	}

	public void setCalBurntPerUnitTime(double calBurntPerUnitTime) {
		this.calBurntPerUnitTime = calBurntPerUnitTime;
	}

	public UnitTime getUnitTime() {
		return unitTime;
	}

	public void setUnitTime(UnitTime unitTime) {
		this.unitTime = unitTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
