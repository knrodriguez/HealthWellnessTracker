package com.HealthWellnessTracker.models;

import java.sql.Timestamp;

public class UserRecord {

	private long recordID;
	private long userID;
	private Timestamp startDate;
	private Timestamp endDate;
	private String recordType;
	private long recordTypeID;
	private String recordNotes;
	
	public UserRecord() {
		recordID = 0;
		userID = 0;
		startDate = new Timestamp(System.currentTimeMillis());
		endDate = null;
		recordType = "";
		recordTypeID = 0;
		recordNotes = "";
	}

	public long getRecordID() {
		return recordID;
	}

	public void setRecordID(long recordID) {
		this.recordID = recordID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
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

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
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

}
