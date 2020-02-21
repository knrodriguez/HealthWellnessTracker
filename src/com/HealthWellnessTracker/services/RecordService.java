package com.HealthWellnessTracker.services;

import java.sql.Time;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.HealthWellnessTracker.DAOs.RecordDAO;
import com.HealthWellnessTracker.models.Record;
import com.HealthWellnessTracker.models.UserProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RecordService {

	private RecordDAO recordDAO = new RecordDAO();
	
	public boolean createRecord(Record record) {
		boolean flag = false;
		flag = recordDAO.insertRecord(record);
		return flag;
	}
	
	public List<Record> findRecordsByUser(UserProfile user) {
		List<Record> recordsList = recordDAO.getRecordsByUserId(user);
		return recordsList;
	}
	
	public Record findRecordByRecordId(long recordId) {
		Record record = recordDAO.getRecordByRecordId(recordId);
		return record;
	}
	
	public boolean editRecord(Record record) {
		boolean flag = false;
		int numUpdatedRecords = recordDAO.updateRecord(record);
		if (numUpdatedRecords != 1) flag = true;
		return flag;
	}

	public boolean removeRecord(long recordId) {
		boolean error = false;
		int numDeletedRecords = recordDAO.deleteRecord(recordId);
		if(numDeletedRecords != 1) error = true;
		return error; 
	}
	
	public String generateJSON(UserProfile user) {
		String json = "";
		List<Record> recordList = recordDAO.getRecordsByUserId(user);
		JSONArray jsonArr = new JSONArray();
		ObjectMapper objMapper = new ObjectMapper();
		if(recordList.size() >= 0) {
			for(Record record: recordList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", record.getRecordId());
				jsonObj.put("title", record.getRecordName());
				jsonObj.put("start", record.getStartDate()+"T"+record.getStartTime());
				jsonObj.put("end", record.getEndDate()+"T"+record.getEndTime());
				jsonObj.put("eventName", record.getEvent().getEventName());
				jsonObj.put("eventId", record.getEvent().getEventId());
				jsonObj.put("notes", record.getRecordNotes());
				jsonObj.put("allDay", isAllDay(record));
				jsonObj.put("recordStartTime", record.getStartTime());
				jsonObj.put("recordEndTime", record.getEndTime());
				jsonArr.put(jsonObj);
			}
			
			try {
				json = objMapper.writeValueAsString(jsonArr.toString());
			} catch (JsonProcessingException e) {e.printStackTrace();}
		}
		System.out.println(json);
		return json;
	}
	
	public Record addTime(Record record, String startTime, String endTime) {
		record.setStartTime(
				startTime.isEmpty() ? null : 
					Time.valueOf(startTime.length()==5 ? startTime+":00" : startTime));
		record.setEndTime(
				endTime.isEmpty() ? null : 
					Time.valueOf(endTime.length()==5 ? endTime+":00" : endTime));
		return record;
	}
	
	public boolean isAllDay(Record record) {
		if(record.getStartTime() == null && record.getEndTime() == null 
				&& record.getStartDate().equals(record.getEndDate())) {
			return true;
		} else return false;
	}
	
}
