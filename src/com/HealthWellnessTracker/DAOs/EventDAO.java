package com.HealthWellnessTracker.DAOs;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.UserProfile;

public class EventDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("HealthWellnessTrackerFactory");
	
	//create
	public boolean insertEvent(Event newEvent) {
		EntityManager em = emf.createEntityManager();
		boolean flag = false;
		try{
			em.getTransaction().begin();
			em.persist(newEvent);
			em.getTransaction().commit();
			flag = true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("Error creating new Event, invalid parametes inputted");
		}
		em.close();
		return flag;
	}
	
	//read
	public List<Event> selectEventByEventName(UserProfile userProfile, String eventName) {
		EntityManager em = emf.createEntityManager();
		List<Event> eventList = null;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT e FROM Event e "
										+ "WHERE e.eventName LIKE :eventName "
										+ "AND e.userProfile = :userProfile "
										+ "ORDER BY e.eventName asc");
			query.setParameter("eventName", eventName)
				 .setParameter("userProfile", userProfile);
			eventList = query.getResultList();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		return eventList;		
	}
	
	//read
	public List<Event> selectEventByUserId(UserProfile userProfile) {
		EntityManager em = emf.createEntityManager();
		List<Event> eventList = null;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT e from Event e WHERE e.userId = :userId");
			query.setParameter("userId", userProfile.getUserId());
			eventList = query.getResultList();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		return eventList;		
	}
	
	//update
	public int updateEvent(Event updatedEvent) {
		EntityManager em = emf.createEntityManager();
		int numUpdatedEvents = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("UPDATE Event e SET e.eventName = :eventName,"
					+ "e.eventCategory = :eventCategory,"
					+ "e.eventDescription = :eventDescription "
					+ "WHERE e.eventId = :eventId");
			query.setParameter("eventName", updatedEvent.getEventName())
				 .setParameter("eventCategory", updatedEvent.getEventCategory())
				 .setParameter("eventDescription", updatedEvent.getEventDescription())
				 .setParameter("eventId", updatedEvent.getEventId());
			numUpdatedEvents = query.executeUpdate();
			em.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		return numUpdatedEvents;
	}
	
	//delete
	public int deleteEvent(Event deletedEvent) {
		EntityManager em = emf.createEntityManager();
		int numDeletedEvents = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE FROM Event e WHERE eventId = :eventId");
			query.setParameter("eventId", deletedEvent.getEventId());
			numDeletedEvents = query.executeUpdate();
			em.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		return numDeletedEvents;
	}
}
