package com.HealthWellnessTracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.UserProfile;
import com.HealthWellnessTracker.services.LoginService;
import com.HealthWellnessTracker.services.LoginService.LoginError;
import com.HealthWellnessTracker.services.UserProfileService;

@Controller
@SessionAttributes("userLogin")
public class AccountController {

	private LoginService loginService = new LoginService();
	
	//create instance of Session Attribute "userLogin"
	@ModelAttribute("userLogin")
	public Login instantiateUserLogin() {
		return new Login();
	}	

//-----------------------------------Login-----------------------------------------
	@RequestMapping(value= {"/login"}, method = RequestMethod.GET)
	public String showLoginForm(@ModelAttribute("inputLogin") Login inputLogin) {
		return "login";
	}
		
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public ModelAndView submitLoginForm(@ModelAttribute("inputLogin") Login inputLogin,
			@ModelAttribute("userLogin") Login userLogin) {
		//System.out.println("userID: " + userLogin.getUserId() + " username: " + userLogin.getUsername());
		Login tempLogin = loginService.logOn(inputLogin);	
		if(tempLogin == null) {
			return new ModelAndView("login","message",LoginError.INCORRECT_PASSWORD.toString());
		}		
		else {
			userLogin.setUserId(tempLogin.getUserId());
			userLogin.setUsername(tempLogin.getUsername());
			userLogin.setPassword(tempLogin.getPassword());
			return new ModelAndView("redirect:/userProfile");
		}			
	}

//-----------------------------------Logout----------------------------------------------
	@RequestMapping("/logout")
	public String logout (@ModelAttribute("userLogin") Login userLogin, SessionStatus session) {
		userLogin = new Login();
		session.setComplete();
		return "redirect:/";
	}
	
//-----------------------------------User Profile-----------------------------------------
	@RequestMapping(value = {"/showUserProfileForm","/createUserProfile"}, method = RequestMethod.GET)
	public ModelAndView showUserProfileForm(@ModelAttribute("userProfile") UserProfile newUserProfile, 
			@ModelAttribute("userLogin") Login userLogin) {
		newUserProfile = new UserProfile(userLogin);
		return new ModelAndView("createUserProfile", "newUserProfile", newUserProfile); 
	}
	
	@RequestMapping(value = "/saveUserProfile", method = RequestMethod.POST)
	public ModelAndView submitUserProfileForm(@ModelAttribute("userLogin") Login userLogin,
			@ModelAttribute("userProfile") UserProfile newUserProfile) {
		System.out.println("UserId: " + userLogin.getUserId());
		UserProfileService userProfileService = new UserProfileService();
		newUserProfile.setUserId(userLogin.getUserId());
		boolean success = userProfileService.createNewUserProfile(newUserProfile);
		if(!success) return new ModelAndView("createUserProfile");
		else return new ModelAndView("redirect://"); 
	}

	@RequestMapping(value = "/userProfile", method = RequestMethod.GET)
	public String showUserProfile(@ModelAttribute("userLogin") Login userLogin) {
		return "viewUserProfile";
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
