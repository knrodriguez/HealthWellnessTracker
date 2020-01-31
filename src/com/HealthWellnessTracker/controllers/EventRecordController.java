package com.HealthWellnessTracker.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.Record;
import com.HealthWellnessTracker.models.UserProfile;
import com.HealthWellnessTracker.services.EventService;
import com.HealthWellnessTracker.services.RecordService;

@Controller
@SessionAttributes(value= {"user"})
public class EventRecordController {
	
	@ModelAttribute("user")
	public UserProfile user(HttpServletRequest request) {
		UserProfile user = (UserProfile) request.getSession().getAttribute("user");
		if(user == null) {
			user = new UserProfile();
		}
		return user;
	}

//-----------------------------------New Event----------------------------------------------	
	@RequestMapping(value = "/newEvent", method = RequestMethod.GET)
	public ModelAndView showNewEventForm(@ModelAttribute("user") UserProfile user,
			@ModelAttribute("newEvent") Event newEvent) {
		ModelAndView mav = new ModelAndView();
		List<String> listEventCategory = new ArrayList<>();
		listEventCategory.add("Habit");
		listEventCategory.add("Symptom");
		listEventCategory.add("Illness");
		for(String s: listEventCategory) {
			System.out.println(s);
		}
		mav.addObject("newEvent");
		mav.addObject("listEventCategory", listEventCategory);
		return mav;
	}
	
	@RequestMapping(value = "/newEvent", method = RequestMethod.POST)
	public ModelAndView submitNewEvent(@ModelAttribute("user") UserProfile user,
			@ModelAttribute("newEvent") Event newEvent) {
		EventService eventService = new EventService();
		String message = eventService.createEvent(newEvent, user);
		
		return new ModelAndView("redirect:myCalendar");
	}
//------------------------------------Records------------------------------------------------
	@RequestMapping(value = "/submitNewRecordForm", method = RequestMethod.POST)
	public ModelAndView submitNewRecordForm(@ModelAttribute("user") UserProfile user,
			@ModelAttribute("newRecord") Record newRecord,
			@RequestParam("event") long eventId) {
		RecordService recordService = new RecordService();
		newRecord.setEndDate(null);
		newRecord.setUserProfile(user);
		boolean error = recordService.createRecord(newRecord);
		if(error) {
			System.out.println("ERROR: could not create new record");
		}
		return new ModelAndView("redirect:myCalendar");
	}
//---------------------------------Show Calendar--------------------------------------------
	@RequestMapping(value = "/myCalendar", method = RequestMethod.GET)
	public ModelAndView showMyCalendar(@SessionAttribute("user") UserProfile user,
			@ModelAttribute("newRecord") Record newRecord) {
		System.out.println(user.toString());
		EventService eventService = new EventService();
		List<Event> eventList = eventService.findEventByUser(user);	
		for(Event e : eventList) {
			System.out.println(e.toString());
		}
		return new ModelAndView("myCalendar","eventList", eventList);
	}
}
