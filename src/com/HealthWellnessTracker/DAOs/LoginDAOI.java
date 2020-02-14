package com.HealthWellnessTracker.DAOs;

import com.HealthWellnessTracker.models.Login;

public interface LoginDAOI {
	
	public boolean insertLogin(Login login);
	public int updateLogin(Login login);
	public int deleteLogin(Login login);
	public Login getLoginByUsername(String username);

}
