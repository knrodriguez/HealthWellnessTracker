package com.HealthWellnessTracker.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "records")
public class UserRecord {

	@Id
	@Column(name = "RecordId")
	private long recordID;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "UserId")
	private UserProfile userProfile;
	
	@Column(name = "StartDate", nullable = false)
	private Timestamp startDate;
	
	@Column(name = "EndDate")
	private Timestamp endDate;
	
	@Column (name = "RecordName", nullable = false)
	private String recordName;
	
	@Column
	private long recordTypeID;
	
	@Column (name = "RecordNotes", nullable = true)
	private String recordNotes;
	
	public UserRecord() {
		recordID = 0;
		startDate = new Timestamp(System.currentTimeMillis());
		endDate = null;
		recordName = "";
		recordTypeID = 0;
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

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public long getRecordTypeID() {
		return recordTypeID;
	}

	public void setRecordTypeID(long recordTypeID) {
		this.recordTypeID = recordTypeID;
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
				+ ", endDate=" + endDate + ", recordName=" + recordName + ", recordTypeID=" + recordTypeID
				+ ", recordNotes=" + recordNotes + "]";
	}

	
}
