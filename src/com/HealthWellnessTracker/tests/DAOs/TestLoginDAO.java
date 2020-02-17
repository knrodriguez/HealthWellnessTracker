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

import com.HealthWellnessTracker.DAOs.LoginDAO;
import com.HealthWellnessTracker.models.Login;

public class TestLoginDAO {

	static Login expectedLogin = null;
	private static LoginDAO loginDAO = new LoginDAO();
	static EntityManagerFactory emf = null; 
	static EntityManager em = null; 
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
	}

	@BeforeEach
	public void setUp() throws Exception {
		expectedLogin = new Login();
		em = emf.createEntityManager();
	}
	
	@Test
	public void testInsertLogin() {
		Login actualLogin = null;
		expectedLogin.setPassword("fdsfsd");
		expectedLogin.setUsername("testInsert");
		loginDAO.insertLogin(expectedLogin);
		actualLogin = em.find(Login.class, expectedLogin.getUserId());
		assertTrue(expectedLogin.equals(actualLogin));
	}
	
	
	@Test
	public void testGetLogin() {
		Login actualLogin = null;
		expectedLogin.setPassword("fdsfsd");
		expectedLogin.setUsername("testGet");
		em.getTransaction().begin();
		em.persist(expectedLogin);
		em.getTransaction().commit();
		actualLogin = loginDAO.getLoginByUsername("testGet");
		assertTrue(expectedLogin.equals(actualLogin));
	}
	
	@Test
	public void testUpdateLogin() {
		int expectedResult = 1, actualResult = 0;
		expectedLogin.setPassword("fdsfsd");
		expectedLogin.setUsername("testUpdate");
		em.getTransaction().begin();
		em.persist(expectedLogin);
		em.getTransaction().commit();
		
		expectedLogin.setPassword("newPass");
		expectedLogin.setUsername("newUsername");
		actualResult = loginDAO.updateLogin(expectedLogin);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testDeleteLogin() {
		int expectedResult = 1, actualResult = 0;
		expectedLogin.setPassword("fdsfsd");
		expectedLogin.setUsername("testDelete");
		em.getTransaction().begin();
		em.persist(expectedLogin);
		em.getTransaction().commit();
		actualResult = loginDAO.deleteLogin(expectedLogin.getUserId());
		assertEquals(expectedResult, actualResult);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM Login l WHERE l.userId = :userId");
		query.setParameter("userId", expectedLogin.getUserId());
		int numDeletedLogins = query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		emf.close();
	}

}
