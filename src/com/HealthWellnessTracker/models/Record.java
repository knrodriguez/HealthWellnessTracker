package com.HealthWellnessTracker.models;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "records")
public class Record {

	@Id
	@Column(name = "RecordId")
	private long recordID;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "UserId")
	private UserProfile userProfile;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "EventId")
	private Event event;
	
	@Column(name = "StartDate", nullable = false)
	private Date startDate;
	
	@Column(name = "StartTime")
	private Time startTime;
	
	@Column(name = "EndDate")
	private Date endDate;
	
	@Column(name = "EndTime")
	private Time endTime;
	
	@Column (name = "RecordName", nullable = false)
	private String recordName;
	
	@Column
	private long recordTypeId;
	
	@Column (name = "RecordNotes", nullable = true)
	private String recordNotes;
	
	public Record() {
		recordID = 0;
		startDate = null;
		endDate = null;
		recordName = "";
		recordTypeId = 0;
		recordNotes = "";
	}

	public long getRecordID() {
		return recordID;
	}

	public void setRecordID(long recordID) {
		this.recordID = recordID;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public long getRecordTypeID() {
		return recordTypeId;
	}

	public void setRecordTypeID(long recordTypeID) {
		this.recordTypeId = recordTypeID;
	}

	public String getRecordNotes() {
		return recordNotes;
	}

	public void setRecordNotes(String recordNotes) {
		this.recordNotes = recordNotes;
	}

	@Override
	public String toString() {
		return "UserRecord [recordID=" + recordID + ", userProfile=" + userProfile + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", recordName=" + recordName + ", recordTypeID=" + recordTypeId
				+ ", recordNotes=" + recordNotes + "]";
	}

	
}
