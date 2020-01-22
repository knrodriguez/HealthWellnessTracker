package com.HealthWellnessTracker.services;

import java.util.List;

import com.HealthWellnessTracker.DAOs.EventDAO;
import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.UserProfile;

public class EventService {

	private EventDAO eventDAO = new EventDAO();
	
	public String createEvent(Event newEvent) {
		boolean success = eventDAO.insertEvent(newEvent);
		if(!success) return "Unable to Create Event :(";
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
		 List<Event> foundEvent = eventDAO.selectEventByEventName(user,"%"+eventName+"%");
		 return foundEvent;
	}
	
	
	
}
