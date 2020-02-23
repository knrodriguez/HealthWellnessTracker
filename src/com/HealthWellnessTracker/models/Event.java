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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	List<Record> recordList = new ArrayList<>();
	
	@NotNull
	@Column (name = "EventCategory", nullable = false)
	private String eventCategory;
	
	@NotNull
	@Size(min = 5, max = 50, message = "Event name must be less than 50 characters.")
	@Column (name = "EventName", nullable = false)
	private String eventName;
	
	@Column (name = "EventDescription", nullable = true)
	private String eventDescription;

	public Event() {
		super();
	}
	
	public Event(String name, String category, String description, UserProfile user) {
		this.eventName = name;
		this.eventCategory = category;
		this.eventDescription = description;
		this.userProfile = user;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
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
	
	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> records) {
		this.recordList = records;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventCategory=" + eventCategory
				+ ", eventName=" + eventName + ", eventDescription=" + eventDescription + "]";
	}
	
	@Override
	public boolean equals(Object event) {
		if(event instanceof Event) {
			Event otherEvent = (Event) event;
			boolean sameId = this.eventId == otherEvent.eventId;
			boolean sameName = this.eventName.equals(otherEvent.eventName);
			boolean sameCat = this.eventCategory.equals(otherEvent.eventCategory);
			boolean sameDesc = this.eventDescription.equals(otherEvent.eventDescription);
			boolean sameUser = this.userProfile.equals(otherEvent.userProfile);	
			if(sameId && sameName && sameCat && sameDesc && sameUser) return true;
		}
		return false;
	}
	
	
	
	
}
