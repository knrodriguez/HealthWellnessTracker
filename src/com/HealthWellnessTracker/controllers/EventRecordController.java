package com.HealthWellnessTracker.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.HealthWellnessTracker.models.Event;
import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.Record;
import com.HealthWellnessTracker.models.UserProfile;
import com.HealthWellnessTracker.services.EventService;
import com.HealthWellnessTracker.services.RecordService;

@Controller
@SessionAttributes(value= {"userLogin","user"})
public class EventRecordController {
	
	@ModelAttribute("user")
	public UserProfile user(@ModelAttribute("userLogin") Login userLogin) {
		return new UserProfile(userLogin);
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
	public ModelAndView createNewEvent(@ModelAttribute("user") UserProfile user,
			@ModelAttribute("newEvent") Event newEvent) {
		EventService eventService = new EventService();
		String message = eventService.createEvent(newEvent, user);
		
		return new ModelAndView("redirect:monthlyView");
	}
//------------------------------------Records------------------------------------------------
	@RequestMapping(value = "/submitNewRecordForm", method = RequestMethod.POST)
	public ModelAndView submitNewRecordForm(@ModelAttribute("user") UserProfile user,
			@ModelAttribute("newRecord") Record newRecord) {
		RecordService recordService = new RecordService();
		newRecord.setEndDate(null);
		newRecord.setUserProfile(user);
		boolean error = recordService.createRecord(newRecord);
		if(error) {
			System.out.println("ERROR: could not create new record");
		}
		return new ModelAndView("redirect:weeklyView");
	}
//---------------------------------Show Calendar--------------------------------------------
	@RequestMapping(value = "/monthlyView", method = RequestMethod.GET)
	public String showMonthlyView(@ModelAttribute("user") UserProfile user) {
		return "monthlyView";
	}
	
	@RequestMapping(value = "/weeklyView", method = RequestMethod.GET)
	public String showWeeklyView(@ModelAttribute("user") UserProfile user, 
			@ModelAttribute("newRecord") Record newRecord) {
		return "weeklyView";
	}
}
