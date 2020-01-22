package com.HealthWellnessTracker.services;


import com.HealthWellnessTracker.DAOs.UserProfileDAO;
import com.HealthWellnessTracker.models.UserProfile;

public class UserProfileService {
	
	private UserProfileDAO userProfileDAO = new UserProfileDAO();
	
	//createNewUser
	public boolean createNewUserProfile(UserProfile userProfile) {
		return userProfileDAO.insertUserProfile(userProfile);
	}
	
	public int editUserProfile(UserProfile userProfile) {
		userProfileDAO = new UserProfileDAO();
		//if(userProfileDAO.updateUserProfile(userProfile)) return 1;
		//else return 0;
		return 0;
	}
	
	
}
