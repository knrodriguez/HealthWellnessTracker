package com.HealthWellnessTracker.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.UserProfile;
import com.HealthWellnessTracker.models.UserRecord;

public class UserRecordDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
	
	//create
	public boolean insertUserRecord(UserRecord record) {
		EntityManager em = emf.createEntityManager();
		boolean flag = false;
		try{
			em.getTransaction().begin();
			em.persist(record);
			em.getTransaction().commit();
			flag = true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("Error creating new Record, invalid parametes inputted");
		}
		em.close();
		return flag;
	}
	
	//read
	public List<UserRecord> selectRecordByEventName(String eventName) {
		EntityManager em = emf.createEntityManager();
		List<UserRecord> recordList = null;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT ur from UserRecord ur "
										+ "JOIN Event e ON ur.eventId = e.eventId"
										+ "WHERE e.eventName = :eventName;");
			query.setParameter("eventName", eventName);
			recordList = query.getResultList();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		return recordList;		
	}
	
	//read
	public List<UserRecord> selectRecordByUserId(UserProfile userProfile) {
		EntityManager em = emf.createEntityManager();
		List<UserRecord> recordList = null;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT e from UserRecord e WHERE e.userId = :userId;");
			query.setParameter("userId", userProfile.getUserId());
			recordList = query.getResultList();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		return recordList;		
	}
	
	//update
	public int updateRecord(UserRecord updatedRecord) {
		EntityManager em = emf.createEntityManager();
		int numUpdatedRecords = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("UPDATE UserRecord e SET e.startDate = :startDate,"
					+ "e.endDate = :endDate,"
					+ "e.recordName = :recordName"
					+ "e.recordTypeId = :recordTypeId"
					+ "e.recordNotes = :recordNotes"
					+ "WHERE recordId = :recordId;");
			query.setParameter("startDate", updatedRecord.getStartDate())
				 .setParameter("endDate", updatedRecord.getEndDate())
				 .setParameter("recordName", updatedRecord.getRecordName())
				 .setParameter("recordTypeId", updatedRecord.getRecordTypeID())
				 .setParameter("recordNotes", updatedRecord.getRecordNotes())
				 .setParameter("recordId", updatedRecord.getRecordID());
			numUpdatedRecords = query.executeUpdate();
			em.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		return numUpdatedRecords;
	}
	
	//delete
	public int deleteRecord(UserRecord deletedRecord) {
		EntityManager em = emf.createEntityManager();
		int numUpdatedRecords = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE FROM UserRecord e WHERE e.recordId = :recordId;");
			query.setParameter("eventId", deletedRecord.getRecordID());
			numUpdatedRecords = query.executeUpdate();
			em.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		return numUpdatedRecords;
	}
	
}
