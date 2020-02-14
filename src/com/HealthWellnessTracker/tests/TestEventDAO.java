package com.HealthWellnessTracker.tests;

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

class TestEventDAO {
	
	static Event expectedEvent = null;
	private static EventDAO eventDAO = new EventDAO();
	static EntityManagerFactory emf = null; 
	static EntityManager em = null; 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
	}

	@BeforeEach
	void setUp() throws Exception {
		expectedEvent = new Event();
		em = emf.createEntityManager();
	}
	
	@Test
	void testInsertLogin() {
		em.getTransaction().begin();
		em.persist(expectedLogin);
		em.getTransaction().commit();
		Login actualLogin = em.find(Login.class, expectedLogin.getUserId());
		assertTrue(expectedLogin.equals(actualLogin));
	}
	
	
	@Test
	void testGetLogin() {

		em.getTransaction().begin();
		em.persist(expectedLogin);
		em.getTransaction().commit();
		Login actualLogin = loginDAO.getLoginByUsername("testGet");
		assertTrue(expectedLogin.equals(actualLogin));
	}
	
	@Test
	void testUpdateLogin() {
		int expectedResult = 1;

		em.getTransaction().begin();
		em.persist(expectedLogin);
		em.getTransaction().commit();
		em.getTransaction().begin();
		Query query = em.createQuery("UPDATE Login l SET l.username = :username, "
									+ "l.password = :password WHERE l.userId = :userId");
		query.setParameter("username", "newUsername")
			 .setParameter("password", "newPass")
			 .setParameter("userId", expectedLogin.getUserId());
		int actualResult = query.executeUpdate();
		em.getTransaction().commit();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testDeleteLogin() {
		int expectedResult = 1;

		em.getTransaction().begin();
		em.persist(expectedLogin);
		em.getTransaction().commit();
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM Login l WHERE l.userId = :userId");
		query.setParameter("userId", expectedLogin.getUserId());
		int actualResult = query.executeUpdate();
		em.getTransaction().commit();
		assertEquals(expectedResult, actualResult);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM Login l WHERE l.userId = :userId");
		query.setParameter("userId", expectedLogin.getUserId());
		int numDeletedLogins = query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}


}
