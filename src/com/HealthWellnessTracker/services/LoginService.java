package com.HealthWellnessTracker.services;

import com.HealthWellnessTracker.DAOs.LoginDAO;

import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.StatusCode;


public class LoginService {
	
	static final LoginDAO loginDAO = new LoginDAO();
	
	public StatusCode createLogin(Login login, String password2) {
		Login newLogin = new Login(login.getUsername(), login.getPassword());
		StatusCode returnCode = null;
		//check if username exists
		if (exists(newLogin)) 
			returnCode = StatusCode.USERNAME_ALREADY_EXISTS;
		//check if passwords match
		else if(!password2.equals(login.getPassword()))
			returnCode = StatusCode.PASSWORDS_DONT_MATCH; 	
		else {
			boolean error = loginDAO.insertLogin(newLogin);	
			if(error) returnCode = StatusCode.LOGIN_ERROR;
			else returnCode = StatusCode.LOGIN_CREATED_SUCCESS;
		}
		return returnCode;		
	}
	
	public Login logOn(Login inputLogin) {
		Login loginFromDatabase = loginDAO.getLoginByUsername(inputLogin.getUsername());
		if(loginFromDatabase != null && loginFromDatabase.getPassword().equals(inputLogin.getPassword()))		
			return loginFromDatabase;
		return null;
	}

	public StatusCode changeUsername(String username, String password) {
		StatusCode error = null;
		return error;
	}
	
	public StatusCode changePassword(String username, String password) {
		StatusCode error = null;
		//LoginDAO loginDAO = new LoginDAO();
		loginDAO.getLoginByUsername(username);
		return error;
	}
	
	public StatusCode editLogin(Login updatedLogin) {
		int numLoginsUpdated = loginDAO.updateLogin(updatedLogin);
		if(numLoginsUpdated == 1) return StatusCode.EDIT_LOGIN_SUCCESS;
		else return StatusCode.EDIT_LOGIN_FAILED;
	}
	
	public StatusCode deleteLogin(long userId) {
		int numDeleteLogins = loginDAO.deleteLogin(userId);
		if(numDeleteLogins == 1) return StatusCode.DELETE_LOGIN_SUCCESS;
		else return StatusCode.DELETE_LOGIN_FAILED;
	}	
	
	//find account	
	public Login findAccount(String username) {
		//LoginDAO loginDAO = new LoginDAO();	
		return loginDAO.getLoginByUsername(username);
	}
	
	//test if login exists in database
	public boolean exists(Login login) {
		return (loginDAO.getLoginByUsername(login.getUsername()) == null ? false:true);
	}
	
}
