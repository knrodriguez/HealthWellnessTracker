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
		if(record.getRecordName().isEmpty()) 
			record.setRecordName(record.getEvent().getEventName());
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
		JSONObject jsonObj = null;
		boolean allDay;
		if(recordList.size() >= 0) {
			for(Record record: recordList) {
				jsonObj = new JSONObject();
				allDay = isAllDay(record);
				jsonObj.put("id", record.getRecordId());
				jsonObj.put("title", record.getRecordName());
				jsonObj.put("start", 
					(allDay ? record.getStartDate() : getDateTime(record, true)));
				jsonObj.put("end", 
					(allDay ? record.getEndDate() : getDateTime(record, false)));
				jsonObj.put("eventName", record.getEvent().getEventName());
				jsonObj.put("eventId", record.getEvent().getEventId());
				jsonObj.put("notes", record.getRecordNotes());
				jsonObj.put("allDay", allDay);
				jsonObj.put("recordStartTime", record.getStartTime());
				jsonObj.put("recordEndTime", record.getEndTime());
				jsonArr.put(jsonObj);
			}
			
			try {
				json = objMapper.writeValueAsString(jsonArr.toString());
			} catch (JsonProcessingException e) {e.printStackTrace();}
		}
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
		try {
			if(record.getStartTime() == null && record.getEndTime() == null 
				&& record.getStartDate().equals(record.getEndDate()))
				return true;
		} catch(NullPointerException e) {e.printStackTrace();}
		return false;		
	}

/*
 * Composes DateTime String used by Full Calendar's Event object to show event's date and time for start and end.
 * Parameters: record - model of calendar record, 
 * 			   start - true returns the record's start date and time, false returns the record's end date and time.
 * Returns: String of the amalgamated date and time separated by a "T".
 * Throws: NullPointerException - if the specified element is null 
 */
	public String getDateTime(Record record, boolean start) {
		String dateTime = "";
		try {
			if(start) {
				dateTime = record.getStartDate() + "T" + record.getStartTime();
			} else dateTime = record.getEndDate() + "T" + record.getEndTime();
		} catch(NullPointerException e) {e.printStackTrace();}		
		return dateTime;
	}
	
}
