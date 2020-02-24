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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HealthWellnessTracker.models.Login;
import com.HealthWellnessTracker.models.StatusCode;
import com.HealthWellnessTracker.models.UserProfile;
import com.HealthWellnessTracker.services.LoginService;
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
		return connectedUser;
	}

	@RequestMapping(value = {"","/","/homepage"}, method = RequestMethod.GET)
	public String showHomepage() {
		return "homepage";
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
			return new ModelAndView("login","alertCode",StatusCode.INCORRECT_LOGIN_CREDENTIALS);
		} else {
			connectedUser = userProfileService.findUserByUserId(tempLogin.getUserId());
			session = request.getSession();
			session.setAttribute("connectedUser", connectedUser);
			return new ModelAndView("redirect:/myCalendar","connectedUser",connectedUser);
		}			
	}
	
//-----------------------------------Logout----------------------------------------------
	@RequestMapping("/logout")
	public String logout (@ModelAttribute("connectedUser") UserProfile connectedUser, 
			SessionStatus session) {
		connectedUser = new UserProfile();
		session.setComplete();
		return "redirect:/";
	}
	
//-----------------------------------User Profile-----------------------------------------

	@RequestMapping(value = "/viewUserProfile", method = RequestMethod.GET)
	public String showUserProfile(@ModelAttribute("userProfile") UserProfile userProfile,
			@SessionAttribute("connectedUser") UserProfile connectedUser) {
		return "userProfile";
	}
	
	@RequestMapping(value = "/editUserProfile", method = RequestMethod.POST)
	public String saveUserProfile(@ModelAttribute("userProfile") UserProfile userProfile,
			@SessionAttribute("connectedUser") UserProfile connectedUser,
			RedirectAttributes redirectAttribute) {
		userProfile.setUserLogin(connectedUser.getUserLogin());
		StatusCode alertCode = userProfileService.editUserProfile(userProfile);
		connectedUser = userProfileService.findUserByUserId(connectedUser.getUserLogin().getUserId());
		redirectAttribute.addFlashAttribute("alertCode", alertCode);
		redirectAttribute.addFlashAttribute("connectedUser", connectedUser);
		//return new ModelAndView("redirect:/viewUserProfile", "connectedUser", connectedUser);		
		return "redirect:/viewUserProfile";
	}
	
//-----------------------------------Sign Up-----------------------------------------
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignupPage(@ModelAttribute("newLogin") Login newLogin) {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView submitSignupForm(@ModelAttribute("newLogin") Login newLogin,
		@RequestParam("password2") String password2, RedirectAttributes redirectAttributes) {
		
		StatusCode loginError = loginService.createLogin(newLogin, password2);
		if (loginError.getCode() < 2000) {
			return new ModelAndView("/signup","alertCode",loginError);			
		} else {
			redirectAttributes.addFlashAttribute("alertCode",loginError);
			return new ModelAndView("redirect:/login");	
		}
	}
	
}//end of class
