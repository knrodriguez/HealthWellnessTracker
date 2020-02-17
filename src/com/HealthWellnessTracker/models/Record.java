package com.HealthWellnessTracker.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "records")
public class Record implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RecordId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long recordId;
	
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
		super();
	}

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordID) {
		this.recordId = recordID;
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
		return "UserRecord [recordID=" + recordId + ", userProfile=" + userProfile + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", recordName=" + recordName + ", recordTypeID=" + recordTypeId
				+ ", recordNotes=" + recordNotes + "]";
	}

	@Override
	public boolean equals(Object record) {
		if(record instanceof Record) {
			Record otherRecord = (Record) record;
			boolean sameId = this.recordId == otherRecord.recordId;
			boolean sameName = this.recordName.equals(otherRecord.recordName);
			boolean sameNotes = this.recordNotes.equals(otherRecord.recordNotes);
			boolean sameUser = this.userProfile.equals(otherRecord.userProfile);
			boolean sameStartDate = this.startDate == otherRecord.startDate;
			boolean sameEndDate = this.endDate == otherRecord.endDate;
			boolean sameStartTime = this.startTime == otherRecord.startTime;
			boolean sameEndTime = this.endTime == otherRecord.endTime;
			if(sameId && sameName && sameNotes && sameUser && sameStartDate && sameEndDate && sameStartTime && sameEndTime)
				return true;
		}
		return false;
	}
	
}
