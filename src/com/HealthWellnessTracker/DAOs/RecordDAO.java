package com.HealthWellnessTracker.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.HealthWellnessTracker.models.Record;
import com.HealthWellnessTracker.models.UserProfile;

public class RecordDAO {

	private final String appFactory = "HealthWellnessTrackerFactory";

	public boolean insertRecord(Record record) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		boolean error = false;
		try{
			em.getTransaction().begin();
			em.persist(record);
			em.getTransaction().commit();	
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("Error creating new Record, invalid parametes inputted");
			error = true;
		}
		em.close();
		emf.close();
		return error;
	}
	
	public Record getRecordByRecordId(long recordId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		Record record = null;
		try {
			record = em.find(Record.class, recordId);
		} catch(PersistenceException e) { e.printStackTrace(); }
		em.close();
		emf.close();
		return record;
	}
	
	public List<Record> getRecordsByUserId(UserProfile userProfile) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		List<Record> recordList = null;
		try {
			Query query = em.createQuery("SELECT e from Record e WHERE e.userProfile = :userProfile");
			query.setParameter("userProfile", userProfile);
			recordList = query.getResultList();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return recordList;		
	}
	
	public int updateRecord(Record updatedRecord) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		int numUpdatedRecords = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("UPDATE Record r SET r.startDate = :startDate,"
					+ "r.endDate = :endDate,"
					+ "r.recordName = :recordName,"
					+ "r.startTime = :startTime,"
					+ "r.endTime = :endTime,"
					+ "r.recordNotes = :recordNotes "
					+ "WHERE r.recordId = :recordId");
			query.setParameter("startDate", updatedRecord.getStartDate())
				 .setParameter("endDate", updatedRecord.getEndDate())
				 .setParameter("recordName", updatedRecord.getRecordName())
				 .setParameter("startTime", updatedRecord.getStartTime())
				 .setParameter("endTime", updatedRecord.getEndTime())
				 .setParameter("recordNotes", updatedRecord.getRecordNotes())
				 .setParameter("recordId", updatedRecord.getRecordID());
			numUpdatedRecords = query.executeUpdate();
			em.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return numUpdatedRecords;
	}
	
	public int deleteRecordByRecordId(long recordId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		int numDeletedRecords = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE FROM Record r WHERE r.recordId = :recordId");
			query.setParameter("recordId", recordId);
			numDeletedRecords = query.executeUpdate();
			em.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return numDeletedRecords;
	}	
	
}
