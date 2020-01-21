package com.HealthWellnessTracker.services;

import java.util.List;

import com.HealthWellnessTracker.DAOs.UserRecordDAO;
import com.HealthWellnessTracker.models.UserRecord;

public class UserRecordService {

	private UserRecordDAO userRecordDAO = new UserRecordDAO();
	
	public boolean createUserRecord(UserRecord record) {
		boolean flag = false;
		flag = userRecordDAO.insertUserRecord(record);
		return flag;
	}
	
	public List<UserRecord> findUserRecord(String eventName) {
		List<UserRecord> recordsList = userRecordDAO.selectRecordByEventName(eventName);
		return recordsList;
	}
	
	public boolean editUserRecord(UserRecord record) {
		boolean flag = false;
		int numUpdatedRecords = userRecordDAO.updateRecord(record);
		if (numUpdatedRecords == 1) flag = true;
		return flag;
	}
	
	public boolean deleteUserRecord(UserRecord record) {
		boolean flag = false;
		int numDeletedRecords = userRecordDAO.deleteRecord(record);
		if(numDeletedRecords == 1) flag = true;
		return flag;
	}
	
	
}
