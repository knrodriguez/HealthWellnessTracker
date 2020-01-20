package com.HealthWellnessTracker.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.HealthWellnessTracker.models.Login;

public class LoginDAO implements LoginDAOI{
	
	private final String appFactory = "HealthWellnessTrackerFactory";
	//EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
	
	//add login to database
	public void insertLogin(Login login) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(login);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	//retrieve login from database matching the argument's username
	@Override
	public Login selectLoginByUsername(String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
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
	
	//test if login exists in database
	public boolean exists(Login login) {
		return (selectLoginByUsername(login.getUsername()) == null ? false:true);
	}
	
	//SHOULD THIS BE BOOLEAN??
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
		em.close();
		if(success == 1) return 1;
		else return 0;
	}

	@Override
	public Login selectLoginByUserId() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		Query query = em.createQuery("SELECT e FROM Login e");
//		@SuppressWarnings("unchecked")
//		List<Login> logins = query.getResultList();
//		em.close();
//		emf.close();
//		
//		for(Login l : logins) {
//			if(l.getUsername().equals(login.getUsername()) &&
//					l.getPassword().equals(login.getPassword())) {
//				return l;
//			}
//		}
		return null;
	}
	
//	public void closeEMF() {
//		emf.close();
//	}

	
}
