package com.HealthWellnessTracker.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.HealthWellnessTracker.models.Record;
import com.HealthWellnessTracker.models.UserProfile;

public class RecordDAO implements DAOInterface<Record>{

	@Override
	public boolean insert(Record newObj) {return insertRecord(newObj);}
	@Override
	public Record find(long id) {return getRecordByRecordId(id);}
	@Override
	public int update(Record updatedObj) {return updateRecord(updatedObj);}
	@Override
	public int delete(long id) {return deleteRecord(id);}	
	@Override
	public List<Record> getAll() {return getAllRecords();}
	
	public boolean insertRecord(Record record) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
		EntityManager em = emf.createEntityManager();
		Record record = null;
		try {
			record = em.find(Record.class, recordId);
		} catch(PersistenceException e) { e.printStackTrace(); }
		em.close();
		emf.close();
		return record;
	}
	
	@SuppressWarnings("unchecked")
	public List<Record> getRecordsByUserId(UserProfile userProfile) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
		EntityManager em = emf.createEntityManager();
		int numUpdatedRecords = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("UPDATE Record r SET r.startDate = :startDate,"
					+ "r.endDate = :endDate,"
					+ "r.recordName = :recordName,"
					+ "r.startTime = :startTime,"
					+ "r.endTime = :endTime,"
					+ "r.event = :event,"
					+ "r.recordNotes = :recordNotes "
					+ "WHERE r.recordId = :recordId");
			query.setParameter("startDate", updatedRecord.getStartDate())
				 .setParameter("endDate", updatedRecord.getEndDate())
				 .setParameter("recordName", updatedRecord.getRecordName())
				 .setParameter("startTime", updatedRecord.getStartTime())
				 .setParameter("endTime", updatedRecord.getEndTime())
				 .setParameter("event", updatedRecord.getEvent())
				 .setParameter("recordNotes", updatedRecord.getRecordNotes())
				 .setParameter("recordId", updatedRecord.getRecordId());
			numUpdatedRecords = query.executeUpdate();
			em.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return numUpdatedRecords;
	}
	
	public int deleteRecord(long recordId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
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
	
	@SuppressWarnings("unchecked")
	public List<Record> getAllRecords(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
		EntityManager em = emf.createEntityManager();
		List<Record> allRecords = null;
		try {
			Query query = em.createQuery("SELECT r FROM Record r");
			allRecords = query.getResultList();
		} catch(PersistenceException e) {e.printStackTrace();}
		return allRecords;
	}
}
