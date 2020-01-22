package com.HealthWellnessTracker.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table (name = "profiles")
public class UserProfile implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UserId")
	private long userId;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name = "UserId")
	private Login userLogin;
	
	@OneToMany(mappedBy = "userProfile")
	private List<Event> eventList = new ArrayList<>();
	
	@OneToMany(mappedBy = "userProfile")
	private List<UserRecord> recordList = new ArrayList<>();
	
	@Basic
	@Column(name = "Name")
	private String name;
	
	@Basic
	@Column(name = "Age")
	private byte age;
	
	@Basic
	@Column (name = "Birthdate")
	private Date birthdate;
	
	@Basic
	@Column (name = "Country")
	private String country;
	
	@Basic
	@Column (name = "EmailAddress")
	private String emailAddress;

	public UserProfile() {}
	
	public UserProfile(Login login) {
		this.userId = login.getUserId();
//		this.userLogin = login;
		this.name = "";
		this.age = 0;
		this.birthdate = null;
		this.country = "";
	}

	public Login getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(Login userLogin) {
		this.userLogin = userLogin;
	}

//	public List<Event> getEventList() {
//		return eventList;
//	}
//
//	public void setEventList(List<Event> eventList) {
//		this.eventList = eventList;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userID) {
		this.userId = userID;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
}
