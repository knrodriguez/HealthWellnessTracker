package com.HealthWellnessTracker.models;

public class Question {

	private long questionID;
	private long userID;
	private long symptomID;
	private String questionDescription;
	private byte questionType;
	
	public Question() {
		questionID = 0;
		userID = 0;
		symptomID = 0;
		questionDescription = "";
		questionType = 0;
	}

	public long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getSymptomID() {
		return symptomID;
	}

	public void setSymptomID(long symptomID) {
		this.symptomID = symptomID;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public byte getQuestionType() {
		return questionType;
	}

	public void setQuestionType(byte questionType) {
		this.questionType = questionType;
	}

}
