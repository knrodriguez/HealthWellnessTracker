package com.HealthWellnessTracker.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.UserProfile;

public class EventDAO implements DAOInterface<Event>{

	@Override
	public boolean insert(Event newObj) {return insertEvent(newObj);}
	@Override
	public Event find(long id) {return getEventByEventId(id);}
	@Override
	public int update(Event updatedObj) {return updateEvent(updatedObj);}
	@Override
	public int delete(long id) {return deleteEvent(id);}	
	@Override
	public List<Event> getAll() {return getAllEvents();}
	
	public boolean insertEvent(Event newEvent) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
		EntityManager em = emf.createEntityManager();
		Event foundEvent = null;
		foundEvent = em.find(Event.class, eventId);
		em.close();
		emf.close();
		return foundEvent;
	}

	public List<Event> getEventsByUserId(UserProfile userProfile) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
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

	public int deleteEvent(long deletedEventId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(APP_FACTORY);
		EntityManager em = emf.createEntityManager();
		int numDeletedEvents = 0;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE FROM Event e WHERE e.eventId = :eventId");
			query.setParameter("eventId", deletedEventId);
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
