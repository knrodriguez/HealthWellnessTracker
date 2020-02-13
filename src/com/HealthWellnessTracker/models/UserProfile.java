package com.HealthWellnessTracker.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;


@Entity
@Table (name = "profiles")
public class UserProfile implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@PrimaryKeyJoinColumn(name = "UserId")
	private Login userLogin;
	
	@OneToMany(mappedBy = "userProfile")
	private List<Event> eventList = new ArrayList<>();
	
	@OneToMany(mappedBy = "userProfile")
	private List<Record> recordList = new ArrayList<>();
	
	@Basic
	@Column(name = "Name", nullable = true)
	private String name;
	
	@Basic
	@Column(name = "Age", nullable = true)
	private byte age;
	
	@Basic
	@Column (name = "Birthdate", nullable = true)
	private Date birthdate;
	
	@Basic
	@Column (name = "Country", nullable = true)
	private String country;
	
	@Email(message = "Email address has invaid format.")
	@Column (name = "EmailAddress", nullable = true)
	private String emailAddress;

	public UserProfile() {
		super();
	}
	
	public UserProfile(Login login) {
		this.userLogin = login;
	}

	public Login getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(Login userLogin) {
		this.userLogin = userLogin;
	}

	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Override
	public String toString() {
		return "UserProfile [userLogin=" + userLogin + ", eventList=" + eventList + 
				", recordList=" + recordList + ", name=" + name + ", age=" + age + ", birthdate=" + birthdate + 
				", country=" + country + ", emailAddress=" + emailAddress + "]";
	}
	
}
