package com.HealthWellnessTracker.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.UserProfile;

public class EventDAO {

	private final String appFactory = "HealthWellnessTrackerFactory";
	
	public boolean insertEvent(Event newEvent) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		boolean flag = false;
		try{
			em.getTransaction().begin();
			em.persist(newEvent);
			em.getTransaction().commit();
			flag = true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return flag;
	}

	public Event getEventByEventId(long eventId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		Event foundEvent = null;
		foundEvent = em.find(Event.class, eventId);
		em.close();
		emf.close();
		return foundEvent;
	}
	
	public List<Event> getEventsByEventName(UserProfile userProfile, String eventName) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		List<Event> eventList = null;
		try {
		//	em.getTransaction().begin();
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
		emf.close();
		return eventList;		
	}
	
	public List<Event> getEventsByUserId(UserProfile userProfile) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		List<Event> eventList = null;
		try {
			//em.getTransaction().begin();
			Query query = em.createQuery("SELECT e from Event e WHERE e.userProfile = :userProfile");
			query.setParameter("userProfile", userProfile);
			eventList = query.getResultList();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return eventList;		
	}
	
	public List<Event> getAllEvents(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		List<Event> eventList = null;
		try {
			//em.getTransaction().begin();
			Query query = em.createQuery("SELECT e from Event e");
			eventList = query.getResultList();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return eventList;
	}
	
	public int updateEvent(Event updatedEvent) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
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
		emf.close();
		return numUpdatedEvents;
	}

	public int deleteEvent(Event deletedEvent) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(appFactory);
		EntityManager em = emf.createEntityManager();
		int numDeletedEvents = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE FROM Event e WHERE e.eventId = :eventId");
			query.setParameter("eventId", deletedEvent.getEventId());
			numDeletedEvents = query.executeUpdate();
			em.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
		return numDeletedEvents;
	}
}
