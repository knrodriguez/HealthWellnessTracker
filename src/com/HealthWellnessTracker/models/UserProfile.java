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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.CascadeOnDelete;


@Entity
@Table (name = "profiles")
public class UserProfile implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne(cascade = CascadeType.REMOVE)
	@PrimaryKeyJoinColumn(name = "UserId")
	@CascadeOnDelete
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
	private int age;
	
	@Basic
	@Column (name = "Birthdate", nullable = true)
	private Date birthdate;
	
	@Basic
	@Column (name = "Country", nullable = true)
	private String country;
	
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

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int i) {
		this.age = i;
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
	
	@Override
	public boolean equals(Object userProfile) {
		if(userProfile instanceof UserProfile) {
			UserProfile otherProfile = (UserProfile) userProfile;
			//since all fields in UserProfile can be null, 
			//only compare the primary key userId from Login userLogin
			boolean sameId = this.userLogin.getUserId() == otherProfile.userLogin.getUserId();
			if(sameId) return true;
		}
		return false;
	}
	
}
