package com.HealthWellnessTracker.services;

import java.util.List;

import com.HealthWellnessTracker.DAOs.EventDAO;
import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.StatusCode;
import com.HealthWellnessTracker.models.UserProfile;

public class EventService {

	private EventDAO eventDAO = new EventDAO();
	
	public StatusCode createEvent(Event newEvent, UserProfile user) {
		StatusCode statusCode;
		newEvent.setUserProfile(user);
		if(eventExists(newEvent)) 
			statusCode = StatusCode.DUPLICATE_EVENT_NAME;
		else {
			boolean error = eventDAO.insertEvent(newEvent);
			if(error) 
				statusCode = StatusCode.PERSIST_EVENT_FAILED;
			else statusCode = StatusCode.EVENT_CREATED_SUCCESS;
		}	
		return statusCode;
	}
	
	public boolean eventExists(Event newEvent) {
		List<Event> eventList = eventDAO.getEventsByUserId(newEvent.getUserProfile());
		for(Event event: eventList) {
			if(event.getEventName().equals(newEvent.getEventName())) {
				return true;
			}
		}
		return false;
	}
	
	public StatusCode editEvent(Event updatedEvent, UserProfile user) {
		StatusCode statusCode;
		updatedEvent.setUserProfile(user);
		if(eventExists(updatedEvent)) 
			statusCode = StatusCode.DUPLICATE_EVENT_NAME;
		else {
			int numEventsUpdated = eventDAO.updateEvent(updatedEvent);
			if(numEventsUpdated == 1) statusCode = StatusCode.EDIT_EVENT_SUCCESS;
			else statusCode = StatusCode.EDIT_EVENT_FAILED;
		}	
		return statusCode;
	}
	
	public StatusCode deleteEvent(long deletedEventId) {
		int numEventsDeleted = eventDAO.deleteEvent(deletedEventId);
		if(numEventsDeleted == 1) return StatusCode.DELETE_EVENT_SUCCESS;
		else return StatusCode.DELETE_EVENT_FAILED;
	}
	
	public List<Event> findEventsByUser(UserProfile user) {
		 List<Event> foundEvent = eventDAO.getEventsByUserId(user);
		//foundEvent.sort(c);
		 return foundEvent;
	}
	
	public Event findEventByEventId(long eventId) {
		Event foundEvent = eventDAO.getEventByEventId(eventId);
		return foundEvent;
	}
	
}
