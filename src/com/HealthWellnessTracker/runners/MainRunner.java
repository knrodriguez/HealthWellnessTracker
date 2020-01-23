package com.HealthWellnessTracker.runners;

import java.util.List;

import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.UserProfile;
import com.HealthWellnessTracker.services.EventService;
import com.HealthWellnessTracker.services.LoginService;

public class MainRunner {

	public static void main(String[] args) {
		Login userLogin = logginIn();
		UserProfile userProfile = new UserProfile(userLogin);
		//createNewEvent(userLogin);
		showAllEvents(userLogin);
		updateEvent(userProfile);
	}
	
	public static Login logginIn() {
		Login userLogin = new Login();
		userLogin.setUsername("test");
		userLogin.setPassword("123");
		LoginService loginService = new LoginService();
		Login logon = loginService.logOn(userLogin);
		return logon;
	}
	
	public static void createNewEvent(Login userLogin) {
		UserProfile user = new UserProfile(userLogin);
		Event newEvent = new Event(user);
		newEvent.setEventCategory("Habit");
		newEvent.setEventDescription("Testing is the FK links to userID properly. This is a HABIT entry.");
		newEvent.setEventName("Test HABIT ");
		EventService eventService = new EventService();
		String message = eventService.createEvent(newEvent, user);
//		System.out.println(message);
	}
	
	public static void showAllEvents(Login userLogin) {
		UserProfile user = new UserProfile(userLogin);
		EventService es = new EventService();
		List<Event> eventList = es.findEventByName(user,"%Test%");
		for(Event e: eventList) {
			System.out.println(e.toString());
		}
	}

	public static void updateEvent(UserProfile user) {
		EventService es = new EventService();
		List<Event> eventList = es.findEventByName(user, "Test Habit 1");
		if(eventList.size() == 1) {
			Event editedEvent = eventList.get(0);
			editedEvent.setEventCategory("Illness");
			editedEvent.setEventDescription("CHANGED from symptom to illness.");
			editedEvent.setEventName("Symptom2Illness");
			System.out.println(editedEvent.getEventId());
			String message = es.editEvent(editedEvent);
			System.out.println(message);
		}
		else if (eventList.size() > 1) System.out.println("Too many events with that name. Be more specific!");
		else System.out.println("NO EVENTS!");
	}
}
