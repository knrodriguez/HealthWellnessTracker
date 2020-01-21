package com.HealthWellnessTracker.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.HealthWellnessTracker.models.UserProfile;

public class UserProfileDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
	
	//createUserProfile
	public boolean insertUserProfile(UserProfile user) {	
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
		return flag;
	}
		
	public List<UserProfile> selectUserProfileByUserId(long userId){
		EntityManager em = emf.createEntityManager();
		List<UserProfile> listUserProfile = null;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT up FROM UserProfile up WHERE up.userId = :userId");
			query.setParameter("userId", userId);
			listUserProfile = query.getResultList();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		return listUserProfile;
	}
	
	public int updateUserProfile(UserProfile userProfile) {
		EntityManager em = emf.createEntityManager();
		int updatedUserProfiles = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("UPDATE UserProfile up SET "
					+ "up.name = :name,"
					+ "up.age = :age,"
					+ "up.birthdate = :birthdate,"
					+ "up.country = :country,"
					+ "up.emailAddress = :emailAddress"
					+ "WHERE up.userId = :userId");
			query.setParameter("name", userProfile.getName())
				 .setParameter("age", userProfile.getAge())
				.setParameter("birthdate", userProfile.getBirthdate())
				.setParameter("emailAddress", userProfile.getEmailAddress())
				.setParameter("userId", userProfile.getUserLogin().getUserId());
			updatedUserProfiles = query.executeUpdate();
			em.getTransaction().commit();
		}catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		return updatedUserProfiles;
	}
	
	public int deleteUserProfile(UserProfile userProfile) {
		EntityManager em = emf.createEntityManager();
		int deletedUserProfiles = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE FROM UserProfile up"
					+ "WHERE up.userId = :userId");
			query.setParameter("userId",userProfile.getUserId());
			deletedUserProfiles = query.executeUpdate();
			em.getTransaction().commit();
		}catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		return deletedUserProfiles;
	}
}
