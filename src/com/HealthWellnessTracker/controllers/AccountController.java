package com.HealthWellnessTracker.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
			return new ModelAndView("viewUserProfile","message","Welcome!");
		}			
	}

//-----------------------------------Logout----------------------------------------------
	@RequestMapping("/logout")
	public String logout (@ModelAttribute("userLogin") Login userLogin, SessionStatus session) {
		userLogin = new Login();
		session.setComplete();
		return "login";
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
		boolean success = userProfileService.createNewUser(newUserProfile);
		if(!success) return new ModelAndView("createUserProfile");
		else return new ModelAndView("redirect://"); 
	}

//-----------------------------------Sign Up-----------------------------------------
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignupPage(@ModelAttribute("newLogin") Login newLogin) {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView submitSignupForm(
			@Valid @ModelAttribute("inputLogin") Login inputLogin,
			@RequestParam("password2") String password2,
			BindingResult bindingResult) {
		LoginError loginError = loginService.createLogin(inputLogin, password2);
		if (loginError == null && !bindingResult.hasErrors())
			return new ModelAndView("viewUserProfile","message","Welcome, " + inputLogin.getUsername());			
		else
			return new ModelAndView("signup","message","ERROR " + loginError.getCode() + ": " + loginError.getDescription());	
	}
	
	
}
