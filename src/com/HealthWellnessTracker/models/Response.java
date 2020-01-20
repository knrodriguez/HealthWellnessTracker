package com.HealthWellnessTracker.models;

import java.sql.Timestamp;

public class Response {

	private long responseID;
	private long userID;
	private long optionID;
	private Timestamp responseTimestamp;
	
	public Response() {
		responseID = 0;
		userID = 0;
		optionID = 0;
		responseTimestamp = new Timestamp(System.currentTimeMillis());
	}

	public long getResponseID() {
		return responseID;
	}

	public void setResponseID(long responseID) {
		this.responseID = responseID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getOptionID() {
		return optionID;
	}

	public void setOptionID(long optionID) {
		this.optionID = optionID;
	}

	public Timestamp getResponseTimestamp() {
		return responseTimestamp;
	}

	public void setResponseTimestamp(Timestamp responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}

}
