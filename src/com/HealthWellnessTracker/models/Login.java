package com.HealthWellnessTracker.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "logins")
public class Login implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UserId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Basic
	@Column(name = "Username", unique = true, nullable = false)
	@Pattern(regexp="^[a-zA-Z_0-9-_]+$", 
			message = "Usernames can contain letters (a-z), numbers(0-9), underscore (_) and dash (-) characters.")
	private String username;
	
	@Basic
	@Column(name = "Password", nullable = false)
	@Pattern(regexp="#(?=.*[a-z])(?=.*[A-Z])(?=.*[!@$#%*_-&]).{8-16}$", 
			message = "Passwords must contain:\n one (1) lowercase letter,\n"
					+ "one (1) Uppercase letter,\n"
					+ "one (1) digit (0-9),\n"
					+ "and one (1) special character (!@$#%*_-&).")
	private String password;
	
	@OneToOne(mappedBy = "userLogin")
	private UserProfile userProfile;
	
	public Login() {
		super();
	}
	
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Login(String username, String password, UserProfile userProfile) {
		this.username = username;
		this.password = password;
		this.userProfile = userProfile;
	}

	public long getUserId() {
		return userId;
	}
	
	public String getUserIdAsString() {
		return Long.toString(userId);
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
