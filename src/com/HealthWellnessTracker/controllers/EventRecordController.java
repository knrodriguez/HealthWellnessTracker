package com.HealthWellnessTracker.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
			@ModelAttribute("newEvent") Event newEvent, @ModelAttribute("editedEvent") Event editedEvent) {
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
			@ModelAttribute("newEvent") Event newEvent) {
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
		Event event = new Event();
		event.setEventId(Long.parseLong(eventId));
		String message = eventService.deleteEvent(event);
		System.out.println(message);
		return "redirect:/newEvent";
	}
//------------------------------------Records------------------------------------------------
	@RequestMapping(value = "/editRecord", method=RequestMethod.POST)
	public String editRecord(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("updatedRecord") Record updatedRecord,
			@RequestParam ("recordId") long recordId) {
		System.out.println("at controller, recordNotes= " + updatedRecord.getRecordNotes());
		updatedRecord.setRecordID(recordId);
		boolean error = recordService.editRecord(updatedRecord);
		if(!error) System.out.println("!!!!!--------- no error--------!!!!!!!");
		else System.out.println("!!!!!--------- ERROR --------!!!!!!!");
		return "redirect:/myCalendar";
	}
	
	@RequestMapping(value = "/deleteRecord", method=RequestMethod.POST)
	public String deleteRecord(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@RequestParam("recordId") String recordId) {
		boolean error = recordService.deleteRecordByRecordId(Long.parseLong(recordId));
		if(!error) System.out.println("!!!!!--------- no error--------!!!!!!!");
		else System.out.println("!!!!!--------- ERROR --------!!!!!!!");
		return "redirect:/myCalendar";
	}
	
	@RequestMapping(value = "/submitNewRecordForm", method = RequestMethod.POST)
	public String submitNewRecordForm(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("newRecord") Record newRecord,
			@RequestParam("eventSelected") long eventId) {
		newRecord.setStartTime(null);
		newRecord.setEndTime(null);
		newRecord.setUserProfile(connectedUser);
		newRecord.setEvent(eventService.findEventByEventId(eventId));
		boolean error = recordService.createRecord(newRecord);
		if(error) {
			System.out.println("ERROR: could not create new record");
		}
		return "redirect:myCalendar";
	}
//---------------------------------Show Calendar--------------------------------------------
	@RequestMapping(value = "/myCalendar", method = RequestMethod.GET, produces ="application/json")
	//@ResponseBody String recordList
	public ModelAndView showMyCalendar(@SessionAttribute("connectedUser") UserProfile connectedUser,
			@ModelAttribute("newRecord") Record newRecord, 
			@ModelAttribute("updatedRecord") Record updatedRecord) {
		System.out.println("at my calendar: " + connectedUser.getName());
		List<Event> eventList = eventService.findEventByUser(connectedUser);
		List<Record> recordList = recordService.findRecordsByUser(connectedUser);
		JSONArray jsonArr = new JSONArray();
		for(Record record: recordList) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("id", record.getRecordID());
			jsonObj.put("title", record.getRecordName());
			jsonObj.put("start", record.getStartDate());
			jsonObj.put("end", record.getEndDate());
			jsonObj.put("eventName", record.getEvent().getEventName());
			jsonObj.put("eventId", record.getEvent().getEventId());
			jsonObj.put("notes", record.getRecordNotes());
			jsonObj.put("allDay",false);
			jsonArr.put(jsonObj);
		}
		ObjectMapper objMapper = new ObjectMapper();
		ModelAndView calendarMAV = new ModelAndView("myCalendar");
		calendarMAV.addObject("eventList",eventList);
		try {
			calendarMAV.addObject("recordList", objMapper.writeValueAsString(jsonArr.toString()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return calendarMAV;
	}
}
