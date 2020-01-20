package com.HealthWellnessTracker.DAOs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.HealthWellnessTracker.models.UserProfile;

public class UserProfileDAO {

	//updateUserProfile
	public boolean createUserProfile(UserProfile user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();
			emf.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			em.close();
			emf.close();
			return false;
		}
	}
	
}
