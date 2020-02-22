package com.HealthWellnessTracker.models;

public enum StatusCode {

	ERROR(1000),
	LOGIN_ERROR(1001, "Login was unable to be completed."),
	USERNAME_ALREADY_EXISTS(1002, "Username already exists."), 
	PASSWORDS_DONT_MATCH(1003, "Passwords do not match."), 
	INCORRECT_LOGIN_CREDENTIALS(1004, "Incorrect e-mail and/or password."),
	DUPLICATE_EVENT_NAME(1005, "Unable to Add Event: Event with that name already exists."),
	PERSIST_EVENT_FAILED(1006, "Unable to Add Event. Please try again later."),
	SUCCESS(2000),
	LOGIN_CREATED(2001, "Your account has been created successfully."),
	EVENT_CREATED(2002, "Added Event ");
	
	int code;
	String message;
	
	StatusCode(){}
	StatusCode(int code){
		this.code = code;
	}
	
	StatusCode(int code, String message){
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	

	@Override
	public String toString() {
		return getMessage(); 
	}
}
