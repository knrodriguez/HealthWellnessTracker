package com.HealthWellnessTracker.controllers;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
@SessionAttributes({"connectedUser"})
public class EventRecordController {
	
	private EventService eventService = new EventService();
	private RecordService recordService = new RecordService();

/*	@ModelAttribute("connectedUser")
	public UserProfile connectedUser(HttpServletRequest request, HttpSession session) {
		UserProfile connectedUser = new UserProfile();
		session = request.getSession();
		if(session.getAttribute("connectedUser") != null ) {
			System.out.println("Record: WE GOT AN ATTRIBUTE");
			connectedUser = (UserProfile) session.getAttribute("connectedUser");
		} else {
			session.setAttribute("connectedUser", connectedUser);
		}
		System.out.println("user's name on record controller: " + connectedUser.getName());
		return connectedUser;
	}*/
	
//-----------------------------------New Event----------------------------------------------	
	@RequestMapping(value = "/newEvent", method = RequestMethod.GET)
	public ModelAndView showNewEventForm(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("newEvent") Event newEvent, 
			@ModelAttribute("editedEvent") Event editedEvent) {
		ModelAndView mav = new ModelAndView();
		List<String> listEventCategory = new ArrayList<>();
		listEventCategory.add("Habit");
		listEventCategory.add("Symptom");
		listEventCategory.add("Illness");
		List<Event> userEvents = eventService.findEventByUser(connectedUser);
		mav.addObject("userEvents", userEvents);
		mav.addObject("listEventCategory", listEventCategory);
		return mav;
	}
	
	@RequestMapping(value = "/newEvent", method = RequestMethod.POST)
	public String submitNewEvent(@ModelAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("newEvent") @Valid Event newEvent) {
		String message = eventService.createEvent(newEvent, connectedUser);		
		return "redirect:myCalendar";
	}
	
	@RequestMapping(value = "/editEvent", method = RequestMethod.POST)
	public String editEvent(@ModelAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("editedEvent") Event editedEvent, @ModelAttribute("newEvent") Event newEvent) {
		String message = eventService.editEvent(editedEvent);
		System.out.println(message);
		return "redirect:/newEvent";
	}
	
	@RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
	public String deleteEvent(@ModelAttribute("connectedUser") UserProfile connectedUser,
			@RequestParam("eventId") String eventId) {
		String message = eventService.deleteEvent(Long.parseLong(eventId));
		System.out.println(message);
		return "redirect:/newEvent";
	}
//------------------------------------Records------------------------------------------------
	@RequestMapping(value = "/editRecord", method=RequestMethod.POST)
	public String editRecord(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("record") Record updatedRecord,
			@RequestParam ("recordId") long recordId, 
			@RequestParam("eventId") long eventId) {
		System.out.println("at controller, recordNotes= " + updatedRecord.getRecordNotes());
		updatedRecord.setRecordId(recordId);
		Event newEvent = eventService.findEventByEventId(eventId);
		updatedRecord.setEvent(newEvent);
		boolean error = recordService.editRecord(updatedRecord);
		return "redirect:/myCalendar";
	}
	
	@RequestMapping(value = "/deleteRecord", method=RequestMethod.POST)
	public String deleteRecord(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@RequestParam("recordId") String recordId) {
		boolean error = recordService.removeRecord(Long.parseLong(recordId));
		return "redirect:/myCalendar";
	}
	
	@RequestMapping(value = "/submitNewRecordForm", method = RequestMethod.POST)
	public String submitNewRecordForm(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("record") Record newRecord,
			@RequestParam("eventId") long eventId,
			@RequestParam("timeStarts") String startTime,
			@RequestParam("timeEnds") String endTime) {
		newRecord.setStartTime(Time.valueOf(startTime+":00")); 
		newRecord.setEndTime(Time.valueOf(endTime+":00"));	 
		newRecord.setUserProfile(connectedUser);
		newRecord.setEvent(eventService.findEventByEventId(eventId));
		boolean error = recordService.createRecord(newRecord);
		return "redirect:myCalendar";
	}
//---------------------------------Show Calendar--------------------------------------------
	@RequestMapping(value = "/myCalendar", method = RequestMethod.GET)
	public ModelAndView showMyCalendar(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("record") Record record) {
		List<Event> eventList = eventService.findEventByUser(connectedUser);
		String recordsJSON = recordService.generateJSON(connectedUser);
		ModelAndView calendarMAV = new ModelAndView("myCalendar");
		calendarMAV.addObject("eventList",eventList)
				   .addObject("recordList", recordsJSON);
		return calendarMAV;
	}
}
