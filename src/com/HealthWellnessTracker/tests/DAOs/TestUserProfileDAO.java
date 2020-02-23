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

import com.HealthWellnessTracker.DAOs.UserProfileDAO;
import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.UserProfile;

public class TestUserProfileDAO {

	static Login login = new Login("fakeUser","fakePass");
	static UserProfile expectedUserProfile = new UserProfile();
	private static UserProfileDAO userDAO = new UserProfileDAO();
	static EntityManagerFactory emf = null; 
	static EntityManager em = null, emClass = null;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
		emClass = emf.createEntityManager();
		emClass.getTransaction().begin();
		emClass.persist(login);
		emClass.getTransaction().commit();
	}

	@BeforeEach
	public void setUp() throws Exception {
		em = emf.createEntityManager();
		expectedUserProfile.setUserLogin(login);
		expectedUserProfile.setAge(20);
		expectedUserProfile.setBirthdate(Date.valueOf(LocalDate.now()));
		expectedUserProfile.setCountry("USA");
		expectedUserProfile.setEmailAddress("fake@gail.com");
		expectedUserProfile.setName("Fake Name");
	}
	
	@Test
	public void testInsertLogin() {
		UserProfile actualUserProfile = null;
		userDAO.insertUserProfile(expectedUserProfile);
		actualUserProfile = em.find(UserProfile.class, expectedUserProfile.getUserLogin().getUserId());
		assertTrue(expectedUserProfile.equals(actualUserProfile));
	}
	
	
	@Test
	public void testGetLogin() {
		UserProfile actualUserProfile = null;
		em.getTransaction().begin();
		em.persist(expectedUserProfile);
		em.getTransaction().commit();
		actualUserProfile = userDAO.getUserProfileByUserId(expectedUserProfile.getUserLogin().getUserId());
		assertTrue(expectedUserProfile.equals(actualUserProfile));
	}
	
	@Test
	public void testUpdateLogin() {
		int expectedResult = 1, actualResult = 0;
		em.getTransaction().begin();
		em.persist(expectedUserProfile);
		em.getTransaction().commit();
		expectedUserProfile.setAge(1);
		expectedUserProfile.setCountry("FR");
		expectedUserProfile.setEmailAddress("UPDATEDfake@FAKE.com");
		expectedUserProfile.setName("FAKER NAME");
		actualResult = userDAO.updateUserProfile(expectedUserProfile);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testDeleteLogin() {
		int expectedResult = 1, actualResult = 0;
		em.getTransaction().begin();
		em.persist(expectedUserProfile);
		em.getTransaction().commit();
		actualResult = userDAO.deleteUserProfile(expectedUserProfile.getUserLogin().getUserId());
		assertEquals(expectedResult, actualResult);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM UserProfile up WHERE up.userLogin = :userLogin");
		query.setParameter("userLogin", expectedUserProfile.getUserLogin());
		int numDeletedItems = query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		emClass.getTransaction().begin();
		Query query = emClass.createQuery("DELETE FROM Login l WHERE l.userId = :userId");
		query.setParameter("userId", login.getUserId());
		int numDeletedLogins = query.executeUpdate();
		emClass.getTransaction().commit();
		emClass.close();
		emf.close();
	}

}
