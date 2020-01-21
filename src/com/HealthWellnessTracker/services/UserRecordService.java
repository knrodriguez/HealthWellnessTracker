package com.HealthWellnessTracker.services;

import com.HealthWellnessTracker.DAOs.UserRecordDAO;
import com.HealthWellnessTracker.models.UserProfile;
import com.HealthWellnessTracker.models.UserRecord;

public class UserRecordService {

	private UserRecordDAO userRecordDAO = new UserRecordDAO();
	
	public boolean createUserRecord(UserProfile user, UserRecord record) {
		boolean flag = false;
		record.setUserProfile(user);
		flag = userRecordDAO.insertUserRecord(record);
		return flag;
	}
	
	
}
