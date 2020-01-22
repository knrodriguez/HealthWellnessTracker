package com.HealthWellnessTracker.DAOs;

import com.HealthWellnessTracker.models.UserProfile;

public interface UsersDAOI {

	public int insertUser(UserProfile user);
	public UserProfile selectUser();
	public int updateUser(UserProfile user);
	public int deleteUser(UserProfile user);
	
}
