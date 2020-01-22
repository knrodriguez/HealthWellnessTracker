package com.HealthWellnessTracker.DAOs;

import com.HealthWellnessTracker.models.Login;

public interface LoginDAOI {
	
	public void insertLogin(Login login);
	public Login selectLoginByUserId();
	public int updateLogin(Login login);
	public int deleteLogin(Login login);
	public Login selectLoginByUsername(String username);

}
