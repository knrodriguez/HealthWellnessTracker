package com.HealthWellnessTracker.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@RequestMapping(value = "/myEvents", method = RequestMethod.GET)
	public ModelAndView showNewEventForm(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("newEvent") Event newEvent, 
			@ModelAttribute("editedEvent") Event editedEvent) {
		ModelAndView mav = new ModelAndView();
		List<String> listEventCategory = new ArrayList<>();
		listEventCategory.add("Habit");
		listEventCategory.add("Symptom");
		listEventCategory.add("Illness");
		List<Event> userEvents = eventService.findEventsByUser(connectedUser);
		mav.addObject("userEvents", userEvents);
		mav.addObject("listEventCategory", listEventCategory);
		return mav;
	}
	
	@RequestMapping(value = "/myEvents", method = RequestMethod.POST)
	public String submitNewEvent(@ModelAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("newEvent") @Valid Event newEvent, RedirectAttributes redirectAttributes) {
		String message = eventService.createEvent(newEvent, connectedUser);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:myEvents";
	}
	
	@RequestMapping(value = "/editEvent", method = RequestMethod.POST)
	public String editEvent(@ModelAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("editedEvent") Event editedEvent, @ModelAttribute("newEvent") Event newEvent) {
		String message = eventService.editEvent(editedEvent);
		System.out.println(message);
		return "redirect:/myEvents";
	}
	
	@RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
	public String deleteEvent(@ModelAttribute("connectedUser") UserProfile connectedUser,
			@RequestParam("eventId") String eventId) {
		String message = eventService.deleteEvent(Long.parseLong(eventId));
		System.out.println(message);
		return "redirect:/myEvents";
	}
//------------------------------------Records------------------------------------------------
	@RequestMapping(value = "/editRecord", method=RequestMethod.POST)
	public String editRecord(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("record") Record updatedRecord,
			@RequestParam ("recordId") long recordId, 
			@RequestParam("eventId") long eventId,
			@RequestParam("timeStarts") String startTime,
			@RequestParam("timeEnds") String endTime) {
		updatedRecord = recordService.addTime(updatedRecord, startTime, endTime);
		updatedRecord.setRecordId(recordId);
		updatedRecord.setEvent(eventService.findEventByEventId(eventId));
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
		newRecord = recordService.addTime(newRecord, startTime, endTime);
		newRecord.setUserProfile(connectedUser);
		newRecord.setEvent(eventService.findEventByEventId(eventId));
		boolean error = recordService.createRecord(newRecord);
		return "redirect:myCalendar";
	}
//---------------------------------Show Calendar--------------------------------------------
	@RequestMapping(value = "/myCalendar", method = RequestMethod.GET)
	public ModelAndView showMyCalendar(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("record") Record record) {
		List<Event> eventList = eventService.findEventsByUser(connectedUser);
		String recordsJSON = recordService.generateJSON(connectedUser);
		ModelAndView calendarMAV = new ModelAndView("myCalendar");
		calendarMAV.addObject("eventList",eventList)
				   .addObject("recordList", recordsJSON);
		return calendarMAV;
	}
}
