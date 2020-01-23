package com.HealthWellnessTracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	//@ModelAttribute
	
	@RequestMapping(value = {"","/","/homepage"}, method = RequestMethod.GET)
	public String showHomepage() {
		return "homepage";
	}


//	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
//	public ModelAndView submitLoginForm(@ModelAttribute("userLogin") Login userLogin, HttpSession session) {
//		LoginService loginService = new LoginService();
//		Login validUser = loginService.logOn(userLogin);
//		//status.setComplete();
//		if(validUser == null) {
//			return new ModelAndView("errorPage","message",LoginError.INCORRECT_PASSWORD.toString());
//		}		
//		else {
//			userLogin.setUserId(validUser.getUserId());
//			userLogin.setUsername(validUser.getUsername());
//			userLogin.setPassword(validUser.getPassword());
//			session.setAttribute("userLogin", userLogin);
//			return new ModelAndView("welcome","message","Welcome!");//.addObject("user", validUser);
//		}
//	}
	
}
