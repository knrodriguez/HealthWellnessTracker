package com.HealthWellnessTracker.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.UserProfile;
import com.HealthWellnessTracker.services.LoginService;
import com.HealthWellnessTracker.services.LoginService.LoginError;
import com.HealthWellnessTracker.services.UserProfileService;

@Controller
@SessionAttributes({"connectedUser"})
public class AccountController {

	private LoginService loginService = new LoginService();
	private UserProfileService userProfileService = new UserProfileService();
	
	@ModelAttribute("connectedUser")
	public UserProfile connectedUser (HttpServletRequest request, HttpSession session) {
		UserProfile connectedUser = new UserProfile();
		session = request.getSession();
		if(session.getAttribute("connectedUser") != null ) {
			connectedUser = (UserProfile) session.getAttribute("connectedUser");
		} else {
			session.setAttribute("connectedUser", connectedUser);
		}
		System.out.println("user's name on account controller: " + connectedUser.getName());
		return connectedUser;
	}
//-----------------------------------Login-----------------------------------------
	@RequestMapping(value= {"/login"}, method = RequestMethod.GET)
	public String showLoginForm(@ModelAttribute("inputLogin") Login inputLogin) {
		return "login";
	}
		
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public ModelAndView submitLoginForm(@ModelAttribute("inputLogin") Login inputLogin,
			@SessionAttribute("connectedUser") UserProfile connectedUser, HttpServletRequest request, HttpSession session) {
		Login tempLogin = loginService.logOn(inputLogin);	
		if(tempLogin == null) {
			return new ModelAndView("login","message",LoginError.INCORRECT_PASSWORD.toString());
		} else {
			//connectedUser.setUserLogin(tempLogin);
			connectedUser = userProfileService.findUserByUserId(tempLogin.getUserId());
			session = request.getSession();
			session.setAttribute("connectedUser", connectedUser);
			return new ModelAndView("redirect:/myCalendar","connectedUser",connectedUser);
		}			
	}
	
//-----------------------------------Logout----------------------------------------------
	//MUST CHANGE TO USER!!!!!!!!!!!!!!!!!!!!!!!
	@RequestMapping("/logout")
	public String logout (@ModelAttribute("userLogin") Login userLogin, SessionStatus session) {
		userLogin = new Login();
		session.setComplete();
		return "redirect:/";
	}
	
//-----------------------------------User Profile-----------------------------------------

	@RequestMapping(value = "/viewUserProfile", method = RequestMethod.GET)
	public String showUserProfile(@ModelAttribute("userProfile") UserProfile userProfile,
			@SessionAttribute("connectedUser") UserProfile connectedUser) {
		System.out.println(connectedUser.getName());
	//	return new ModelAndView("userProfile");
		return "userProfile";
	}
	
	@RequestMapping(value = "/editUserProfile", method = RequestMethod.POST)
	public ModelAndView saveUserProfile(@ModelAttribute("userProfile") UserProfile userProfile,
			@SessionAttribute("connectedUser") UserProfile connectedUser) {
		userProfile.setUserLogin(connectedUser.getUserLogin());
		int numProfilesUpdated = userProfileService.editUserProfile(userProfile);
		if(numProfilesUpdated == 1) {
			connectedUser = userProfileService.findUserByUserId(connectedUser.getUserLogin().getUserId());
			return new ModelAndView("redirect:myCalendar", "connectedUser", connectedUser);
		}	
		else {
			System.out.println("Cannot update profile ERROR! " + numProfilesUpdated);
			return new ModelAndView("userProfile");
		}		
	}
	
//-----------------------------------Sign Up-----------------------------------------
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignupPage(@ModelAttribute("newLogin") Login newLogin) {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView submitSignupForm(
			@ModelAttribute("newLogin") Login newLogin,
			@RequestParam("password2") String password2) {
		LoginError loginError = loginService.createLogin(newLogin, password2);
		if (loginError != null) {
			return new ModelAndView("/homepage","message","ERROR " + loginError.getCode() + ": " + loginError.getDescription());			
		}
		else {
			return new ModelAndView("redirect:/login");	
		}
	}
	
	
}