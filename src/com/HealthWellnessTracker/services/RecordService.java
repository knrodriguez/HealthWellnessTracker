package com.HealthWellnessTracker.services;

import java.util.List;

import com.HealthWellnessTracker.DAOs.RecordDAO;
import com.HealthWellnessTracker.models.Record;
import com.HealthWellnessTracker.models.UserProfile;

public class RecordService {

	private RecordDAO recordDAO = new RecordDAO();
	
	public boolean createRecord(Record record) {
		boolean flag = false;
		flag = recordDAO.insertRecord(record);
		return flag;
	}
	
	public List<Record> findRecord(String eventName) {
		List<Record> recordsList = recordDAO.selectRecordsByEventName(eventName);
		return recordsList;
	}
	
	public boolean editRecord(Record record) {
		boolean flag = false;
		int numUpdatedRecords = recordDAO.updateRecord(record);
		if (numUpdatedRecords == 1) flag = true;
		return flag;
	}
	
	public boolean deleteRecord(Record record) {
		boolean flag = false;
		int numDeletedRecords = recordDAO.deleteRecord(record);
		if(numDeletedRecords == 1) flag = true;
		return flag;
	}
	
	public List<Record> findRecordByUser(UserProfile user) {
		List<Record> recordsList = recordDAO.selectRecordsByUserId(user);
		return recordsList;
	}
	
	
}
