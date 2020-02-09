package com.HealthWellnessTracker.services;

import java.util.List;

import com.HealthWellnessTracker.DAOs.EventDAO;
import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.UserProfile;

public class EventService {

	private EventDAO eventDAO = new EventDAO();
	
	public String createEvent(Event newEvent, UserProfile user) {
		List<Event> eventList = eventDAO.getEventsByUserId(user);
		for(Event event: eventList) {
			if(event.getEventName().equals(newEvent.getEventName())) {
				return "Unable to Create Event: Event with that name already exists.";
			}
		}
		newEvent.setUserProfile(user);
		boolean success = eventDAO.insertEvent(newEvent);
		if(!success) return "Unable to Create Event :( Transaction did not persist.";
		else return "Event Created! :)";
	}
	
	public String editEvent(Event updatedEvent) {
		int numEventsUpdated = eventDAO.updateEvent(updatedEvent);
		if(numEventsUpdated == 1) return updatedEvent.getEventName() + " Updated! :)";
		else return updatedEvent.getEventName() + " Failed to Update. :(";
	}
	
	public String deleteEvent(Event deletedEvent) {
		int numEventsDeleted = eventDAO.deleteEvent(deletedEvent);
		if(numEventsDeleted == 1) return deletedEvent.getEventName() + " Deleted! :)";
		else return deletedEvent.getEventName() + " Failed to Delete. :(";
	}
	
	public List<Event> findEventByName(UserProfile user, String eventName) {
		 List<Event> foundEvent = eventDAO.getEventsByEventName(user,"%"+eventName+"%");
		 return foundEvent;
	}
	
	public List<Event> findEventByUser(UserProfile user) {
		 List<Event> foundEvent = eventDAO.getEventsByUserId(user);
		 return foundEvent;
	}
	
	public Event findEventByEventId(long eventId) {
		Event foundEvent = eventDAO.getEventByEventId(eventId);
		return foundEvent;
	}
	
}