package com.HealthWellnessTracker.services;

import java.util.List;

import com.HealthWellnessTracker.DAOs.EventDAO;
import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.StatusCode;
import com.HealthWellnessTracker.models.UserProfile;

public class EventService {

	private EventDAO eventDAO = new EventDAO();
	
	public String createEvent(Event newEvent, UserProfile user) {
		List<Event> eventList = eventDAO.getEventsByUserId(user);
		for(Event event: eventList) {
			if(event.getEventName().equals(newEvent.getEventName())) {
				return StatusCode.DUPLICATE_EVENT_NAME.toString();
			}
		}
		newEvent.setUserProfile(user);
		boolean success = eventDAO.insertEvent(newEvent);
		if(!success) return "Unable to Create Event :( Transaction did not persist.";
		else return StatusCode.EVENT_CREATED.toString() + newEvent.getEventName() + ".";
	}
	
	public String editEvent(Event updatedEvent) {
		int numEventsUpdated = eventDAO.updateEvent(updatedEvent);
		if(numEventsUpdated == 1) return updatedEvent.getEventName() + " Updated! :)";
		else return updatedEvent.getEventName() + " Failed to Update. :(";
	}
	
	public String deleteEvent(long deletedEventId) {
		int numEventsDeleted = eventDAO.deleteEvent(deletedEventId);
		if(numEventsDeleted == 1) return "Delete successful.";
		else return " Failed to Delete. :(";
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
