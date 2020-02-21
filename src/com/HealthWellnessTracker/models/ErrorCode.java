package com.HealthWellnessTracker.models;

public enum ErrorCode {

//------------------------login--------------------------------------------------------
	LOGIN_ERROR(1, "Login was unable to be completed."),
	USERNAME_ALREADY_EXISTS(2, "Username already exists."), 
	PASSWORDS_DONT_MATCH(3, "Passwords do not match."), 
	INCORRECT_LOGIN_CREDENTIALS(4, "Incorrect e-mail and/or password.");
	
	int errorCode;
	String errorMessage;
	
	ErrorCode(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
	
	@Override
	public String toString() {
		return getErrorMessage(); 
	}

}
