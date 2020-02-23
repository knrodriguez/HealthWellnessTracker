package com.HealthWellnessTracker.tests.DAOs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.HealthWellnessTracker.DAOs.EventDAO;
import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.UserProfile;

public class TestEventDAO {
	
	static Login testLogin = new Login("fakeUsername","fakePassword");
	static UserProfile user = new UserProfile();
	static Event expectedEvent = null;
	private static EventDAO eventDAO = new EventDAO();
	static EntityManagerFactory emf = null; 
	static EntityManager emTest = null;
	static EntityManager emClass = null;
	
	//create user to manipulate
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
		emClass = emf.createEntityManager();
		emClass.getTransaction().begin();
		emClass.persist(testLogin);
		user.setUserLogin(testLogin);
		emClass.persist(user);
		emClass.getTransaction().commit();		
	}

	@BeforeEach
	public void setUp() throws Exception {
		expectedEvent = new Event();
		expectedEvent.setEventName("testName");
		expectedEvent.setEventCategory("testCat");
		expectedEvent.setEventDescription("testDescript");
		expectedEvent.setUserProfile(user);
		emTest = emf.createEntityManager();
	}
	
	@Test
	public void testInsertEvent() {
		Event actualEvent = null;
		eventDAO.insertEvent(expectedEvent);
		actualEvent = emTest.find(Event.class, expectedEvent.getEventId());
		assertTrue(expectedEvent.equals(actualEvent));
	}
	
	
	@Test
	public void testGetEvent() {
		emTest.getTransaction().begin();
		emTest.persist(expectedEvent);
		emTest.getTransaction().commit(); 
		Event actualEvent = null;
		actualEvent = eventDAO.getEventByEventId(expectedEvent.getEventId());
		assertTrue(expectedEvent.equals(actualEvent));
	}
	
	@Test
	public void testUpdateEvent() {
		int expectedResult = 1, actualResult = 0;
		emTest.getTransaction().begin();
		emTest.persist(expectedEvent);
		emTest.getTransaction().commit(); 
		expectedEvent.setEventName("UPDATEDName");
		expectedEvent.setEventCategory("UPDATEDCat");
		expectedEvent.setEventDescription("UPDATEDDescript");
		actualResult = eventDAO.updateEvent(expectedEvent);
		assertEquals(expectedResult, actualResult);	 
	}
	
	@Test
	public void testDeleteEvent() {
		int expectedResult = 1, actualResult = 0;
		emTest.getTransaction().begin();
		emTest.persist(expectedEvent);
		emTest.getTransaction().commit(); 
		actualResult = eventDAO.deleteEvent(expectedEvent.getEventId());
		assertEquals(expectedResult, actualResult);	 
	}

	@AfterEach
	public void tearDown() throws Exception {
		emTest.getTransaction().begin();
		Query query = emTest.createQuery("DELETE FROM Event e WHERE e.eventId = :eventId");
		query.setParameter("eventId", expectedEvent.getEventId());
		int numDeletedEvents = query.executeUpdate();
		emTest.getTransaction().commit();
		emTest.close();
		
	}
	
	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		emClass.getTransaction().begin();
		Query query = emClass.createQuery("DELETE FROM Login l WHERE l.userId = :userId");
		query.setParameter("userId", testLogin.getUserId());
		int numDeletedLogins = query.executeUpdate();
		emClass.getTransaction().commit();
		emClass.close();	
		emf.close();
	}

}
