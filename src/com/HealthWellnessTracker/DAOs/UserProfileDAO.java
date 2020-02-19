package com.HealthWellnessTracker.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.UserProfile;

public class UserProfileDAO implements DAOInterface<UserProfile> {

	@Override
	public boolean insert(UserProfile newObj) {return insertUserProfile(newObj);}
	@Override
	public UserProfile find(long id) {return getUserProfileByUserId(id);}
	@Override
	public int update(UserProfile updatedObj) {return updateUserProfile(updatedObj);}
	@Override
	public int delete(long id) {return deleteUserProfile(id);}	
	@Override
	public List<UserProfile> getAll() {return getAllUserProfiles();}
	
	public boolean insertUserProfile(UserProfile user) {	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
		EntityManager em = emf.createEntityManager();
		int deletedUserProfiles = 0;
		try {
			em.getTransaction().begin();
			Login userLogin = em.find(Login.class, userProfileId);
			Query query = em.createQuery("DELETE FROM UserProfile up"
					+ " WHERE up.userLogin = :userLogin");
			query.setParameter("userLogin",userLogin);
			deletedUserProfiles = query.executeUpdate();
			em.getTransaction().commit();
		}catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return deletedUserProfiles;
	}

	public List<UserProfile> getAllUserProfiles(){
		List<UserProfile> allProfiles = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT up FROM UserProfile up");
			allProfiles = query.getResultList();
		}catch(PersistenceException e) {e.printStackTrace();}
		
		return allProfiles;
	}
}
