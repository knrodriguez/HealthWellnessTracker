package com.HealthWellnessTracker.services;


import com.HealthWellnessTracker.DAOs.UserProfileDAO;
import com.HealthWellnessTracker.models.StatusCode;
import com.HealthWellnessTracker.models.UserProfile;

public class UserProfileService {
	
	private UserProfileDAO userProfileDAO = new UserProfileDAO();
	
	//createNewUser
	public boolean createNewUserProfile(UserProfile userProfile) {
		return userProfileDAO.insertUserProfile(userProfile);
	}
	
	public StatusCode editUserProfile(UserProfile userProfile) {
		int numProfilesUpdated = userProfileDAO.updateUserProfile(userProfile);
		if(numProfilesUpdated == 1) return StatusCode.UPDATE_PROFILE_SUCCESS;
		else return StatusCode.UPDATE_PROFILE_FAILED;
	}
	
	public UserProfile findUserByUserId(long userId) {
		return userProfileDAO.getUserProfileByUserId(userId);
	}
	
	
}
