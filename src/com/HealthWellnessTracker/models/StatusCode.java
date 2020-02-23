package com.HealthWellnessTracker.models;

public enum StatusCode {

	ERROR(1000),
	LOGIN_ERROR(1001, "Login was unable to be completed."),
	USERNAME_ALREADY_EXISTS(1002, "Username already exists."), 
	PASSWORDS_DONT_MATCH(1003, "Passwords do not match."), 
	INCORRECT_LOGIN_CREDENTIALS(1004, "Incorrect e-mail and/or password."),
	DUPLICATE_EVENT_NAME(1005, "Unable to Add Event: Event with that name already exists."),
	PERSIST_EVENT_FAILED(1006, "Unable to Add Event. Please try again later."),
	DELETE_EVENT_FAILED(1007, "Unable to Delete Event. Please try again later."),
	EDIT_EVENT_FAILED(1008, "Unable to Edit Event. Please review your changes and try again later."),
	SUCCESS(2000),
	LOGIN_CREATED_SUCCESS(2001, "Your account has been created successfully."),
	EVENT_CREATED_SUCCESS(2006, "Added Event successfuly."),
	DELETE_EVENT_SUCCESS(2007, "Event has been deleted successfully."),
	EDIT_EVENT_SUCCESS(2008, "Event has been edited successfully.");

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
