package com.HealthWellnessTracker.services;

import com.HealthWellnessTracker.DAOs.LoginDAO;
import com.HealthWellnessTracker.models.Login;

public class LoginService {
	
	static final LoginDAO loginDAO = new LoginDAO();
	
	public enum LoginError {
		USERNAME_ALREADY_EXISTS(1, "Username already exists."), 
		PASSWORDS_DONT_MATCH(2, "Passwords do not match."), 
		INCORRECT_PASSWORD(3, "Incorrect e-mail/password combination."), 
		INCORRECT_ACTIVATION_CODE(4, "Activate code is incorrect.");
	
		private final int code;
		private final String description;
		
		private LoginError(int code, String descript) {
			this.code = code;
			this.description = descript;
		}
		
		public String getDescription() {
			return this.description;
		}
		
		public int getCode() {
			return this.code;
		}		
		
		@Override
		public String toString() {
			return "ERROR " + getCode() + ": " + description; 
		}
	
	}
	
	//create new login
	public LoginError createLogin(Login login, String password2) {
		//LoginDAO loginDAO = new LoginDAO();
		Login newLogin = new Login(login.getUsername(), login.getPassword());
		LoginError error = null;
		//check if username exists
		if (exists(newLogin)) 
			error = LoginError.USERNAME_ALREADY_EXISTS;
		//check if passwords match
		else if(!password2.equals(login.getPassword()))
			error = LoginError.PASSWORDS_DONT_MATCH; 	
		else loginDAO.insertLogin(newLogin);	
		return error;
		
		//extra - check if password meets criteria
	}
	
	//log onto account
	public Login logOn(Login inputLogin) {
		Login loginFromDatabase = loginDAO.selectLoginByUsername(inputLogin.getUsername());
		if(loginFromDatabase != null && loginFromDatabase.getPassword().equals(inputLogin.getPassword()))		
			return loginFromDatabase;
		return null;
	}

	//change username INCOMPLETE
	public LoginError changeUsername(String username, String password) {
		LoginError error = null;
		return error;
	}
	
	//change password INCOMPLETE
	public LoginError changePassword(String username, String password) {
		LoginError error = null;
		//LoginDAO loginDAO = new LoginDAO();
		loginDAO.selectLoginByUsername(username);
		return error;
	}
	
	//forgot password INCOMPLETE
	//delete account INCOMPLETE
	
	//find account	
	public Login findAccount(String username) {
		//LoginDAO loginDAO = new LoginDAO();	
		return loginDAO.selectLoginByUsername(username);
	}
	
	//test if login exists in database
	public boolean exists(Login login) {
		return (loginDAO.selectLoginByUsername(login.getUsername()) == null ? false:true);
	}
	
}
