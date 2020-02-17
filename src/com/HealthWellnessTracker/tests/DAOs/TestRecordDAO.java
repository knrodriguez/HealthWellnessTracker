package com.HealthWellnessTracker.tests.DAOs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.HealthWellnessTracker.DAOs.RecordDAO;
import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.Record;
import com.HealthWellnessTracker.models.UserProfile;

public class TestRecordDAO {

	static Login login = new Login("fakeUsername","fakePassword");
	static UserProfile user = new UserProfile();
	static Event event = new Event("FakeRecord","FakeCat","FakeDescript",user);
	static RecordDAO recordDAO = new RecordDAO();
	static Record expectedRecord = null;
	static EntityManagerFactory emf = null; 
	static EntityManager emTest = null;
	static EntityManager emClass = null;
	
	//create user to manipulate
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
		emClass = emf.createEntityManager();
		emClass.getTransaction().begin();
		emClass.persist(login);
		user.setUserLogin(login);
		emClass.persist(user);
		event.setUserProfile(user);
		emClass.persist(event);
		emClass.getTransaction().commit();		
	}

	@BeforeEach
	public void setUp() throws Exception {
		expectedRecord = new Record();
		expectedRecord.setRecordName("fakeRecord");
		expectedRecord.setStartDate(Date.valueOf(LocalDate.now()));
		expectedRecord.setEvent(event);
		expectedRecord.setRecordNotes("fake Notes");
		expectedRecord.setUserProfile(user);
		emTest = emf.createEntityManager();
	}
	
	@Test
	public void testInsertRecord() {
		Record actualRecord = null;
		recordDAO.insertRecord(expectedRecord);
		actualRecord = emTest.find(Record.class, expectedRecord.getRecordId());
		assertTrue(expectedRecord.equals(actualRecord));
	}
	
	
	@Test
	public void testGetRecord() {
		emTest.getTransaction().begin();
		emTest.persist(expectedRecord);
		emTest.getTransaction().commit(); 
		Record actualRecord = null;
		actualRecord = recordDAO.getRecordByRecordId(expectedRecord.getRecordId());
		assertTrue(expectedRecord.equals(actualRecord));
	}
	
	@Test
	public void testUpdateRecord() {
		int expectedResult = 1, actualResult = 0;
		emTest.getTransaction().begin();
		emTest.persist(expectedRecord);
		emTest.getTransaction().commit(); 
		expectedRecord.setRecordName("UPDATEDName");
		expectedRecord.setRecordNotes("UPDATED notes");
		actualResult = recordDAO.updateRecord(expectedRecord);
		assertEquals(expectedResult, actualResult);	 
	}
	
	@Test
	public void testDeleteRecord() {
		int expectedResult = 1, actualResult = 0;
		emTest.getTransaction().begin();
		emTest.persist(expectedRecord);
		emTest.getTransaction().commit(); 
		actualResult = recordDAO.deleteRecordByRecordId(expectedRecord.getRecordId());
		assertEquals(expectedResult, actualResult);	 
	}

	@AfterEach
	public void tearDown() throws Exception {
		emTest.getTransaction().begin();
		Query query = emTest.createQuery("DELETE FROM Record e WHERE e.recordId = :RecordId");
		query.setParameter("RecordId", expectedRecord.getRecordId());
		int numDeletedRecords = query.executeUpdate();
		emTest.getTransaction().commit();
		emTest.close();
		
	}
	
	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		int numDeletedItems = 0;
		emClass.getTransaction().begin();
		Query query = emClass.createQuery("DELETE FROM Event e WHERE e.eventId = :eventId");
		query.setParameter("eventId", event.getEventId());
		numDeletedItems += query.executeUpdate();
		query = emClass.createQuery("DELETE FROM Login l WHERE l.userId = :userId");
		query.setParameter("userId", login.getUserId());
		numDeletedItems += query.executeUpdate();
		emClass.getTransaction().commit();
		emClass.close();	
		emf.close();
	}

}
