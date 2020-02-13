package com.HealthWellnessTracker.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.HealthWellnessTracker.models.UserProfile;

public class UserProfileDAO {

	private final String appFactory = "HealthWellnessTrackerFactory";
	
	public boolean insertUserProfile(UserProfile user) {	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		boolean flag = false;
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			flag = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return flag;
	}
		
	public UserProfile getUserProfileByUserId(long userId){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		UserProfile foundUser = null;
		try {
			foundUser = em.find(UserProfile.class, userId);
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return foundUser;
	}
	
	public int updateUserProfile(UserProfile userProfile) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		int updatedUserProfiles = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("UPDATE UserProfile up SET "
					+ "up.name = :name,"
					+ "up.age = :age,"
					+ "up.birthdate = :birthdate,"
					+ "up.country = :country,"
					+ "up.emailAddress = :emailAddress "
					+ "WHERE up.userLogin = :userLogin");
			query.setParameter("name", userProfile.getName())
				 .setParameter("age", userProfile.getAge())
				.setParameter("birthdate", userProfile.getBirthdate())
				.setParameter("country", userProfile.getCountry())
				.setParameter("emailAddress", userProfile.getEmailAddress())
				.setParameter("userLogin", userProfile.getUserLogin());
			updatedUserProfiles = query.executeUpdate();
			em.getTransaction().commit();
		}catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return updatedUserProfiles;
	}
	
	public int deleteUserProfile(long userProfileId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		int deletedUserProfiles = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE FROM UserProfile up"
					+ "WHERE up.userId = :userId");
			query.setParameter("userId",userProfileId);
			deletedUserProfiles = query.executeUpdate();
			em.getTransaction().commit();
		}catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return deletedUserProfiles;
	}
}
