package com.HealthWellnessTracker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long eventId;
	//Is a one to one relationship from events pov
	//one eventId can have only one userId
//	@OneToOne
//	//@JoinColumn(name = "")
//	private long userId;	
//	@Basic
//	@Column
//	private long userId; 
	@Column
	private String eventCategory;
	@Column
	private String eventName;
	@Column
	private String eventDescription;

	public Event() {
		//this.user = null; should grab from user logged in
		this.eventCategory = "";
		this.eventName = "";
		this.eventDescription = "";
	}
	
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	
	
	
	
	
}
