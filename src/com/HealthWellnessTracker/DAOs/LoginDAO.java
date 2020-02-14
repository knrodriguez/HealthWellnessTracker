package com.HealthWellnessTracker.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.UserProfile;

public class LoginDAO implements LoginDAOI{
	
	private final String appFactory = "HealthWellnessTrackerFactory";
	
	public boolean insertLogin(Login login) {
		boolean error = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(login);
			em.getTransaction().commit();	
			UserProfile newUserProfile = new UserProfile(login);
			UserProfileDAO userProfileDAO = new UserProfileDAO();
			userProfileDAO.insertUserProfile(newUserProfile);	
		} catch(PersistenceException e) {
			e.printStackTrace();
			error = true;
		}
		em.close();
		emf.close();
		return error;
	}

	@Override
	public Login getLoginByUsername(String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT e FROM Login e WHERE e.username = :username");
		query.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<Login> loginList = query.getResultList();
		em.close();
		emf.close();
		if (loginList.isEmpty()) return null; //return null as no user exists with argument username
		else if (loginList.size() > 1) return null; //NEED TO UPDATE! return null as error that more than 1 username exists in DB 
		else return loginList.get(0); //return first element of resultset (should only be 1)
	}
	
	@Override
	public int updateLogin(Login login) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
		Query query = em.createQuery("UPDATE Login SET username = :username, password = :password WHERE userId = :userId");
		query.setParameter("username", login.getUsername());
		query.setParameter("password", login.getPassword());
		query.setParameter("userId", login.getUserId());
		int success = query.executeUpdate(); //returns the # of entities updated
		em.getTransaction().commit();
		em.close();
		if(success == 1) return 1;
		else return 0;
	}

	@Override
	public int deleteLogin(Login login) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM Login WHERE userId = :userId");
		query.setParameter("userId", login.getUserId());
		int success = query.executeUpdate(); //returns the # of entities updated
		em.getTransaction().commit();
		em.close();
		if(success == 1) return 1;
		else return 0;
	}
	
	public List<Login> getAllLogins(){
		List<Login> allLogins = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT l FROM Login l");
			allLogins = query.getResultList();
		} catch(PersistenceException e) {e.printStackTrace();}
		return allLogins;	
	}
}
