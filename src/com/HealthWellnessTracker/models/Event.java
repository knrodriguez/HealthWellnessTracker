package com.HealthWellnessTracker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//, indexes = {@Index (name = "user_based_events", columnList = "EventName,UserId", unique = true)})

@Entity
@Table(name = "events")		
public class Event implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "EventId")
	private long eventId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "UserId")
	private UserProfile userProfile; 

	@OneToMany(mappedBy = "event")
	List<Record> records = new ArrayList<>();
	
	@Column (name = "EventCategory", nullable = false)
	private String eventCategory;
	
	@Column (name = "EventName", nullable = false)
	private String eventName;
	
	@Column (name = "EventDescription", nullable = true)
	private String eventDescription;

	public Event() {
		super();
	}
	
	public Event(UserProfile userProfile) {
		super();
		this.userProfile = userProfile;
	}
	
	public Event(Event event) {
		this.eventId = event.getEventId();
		this.eventCategory = event.getEventCategory();
		this.eventDescription = event.getEventDescription();
		this.eventName = event.getEventName();
		this.userProfile = event.getUserProfile();
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
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
	
	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventCategory=" + eventCategory
				+ ", eventName=" + eventName + ", eventDescription=" + eventDescription + "]";
	}
	
	
	
	
	
	
}
