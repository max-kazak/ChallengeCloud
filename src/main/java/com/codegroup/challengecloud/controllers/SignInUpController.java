package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class SignInUpController {

	private static Logger logger = Logger.getLogger(SignInUpController.class);
	@Autowired
	UserService userService;

	@ModelAttribute("user")
	public User createUser() {
		return new User();
	}
	
	@RequestMapping(value="/signinup", method = RequestMethod.GET)
	public ModelAndView signInUp(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
 
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("signinup");
 
		return model;
	}

	/**
	* created by Nipel-Crumple 24.03.2015
	 */
	@RequestMapping(value="/signinup/registration", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("user") User user) {
//		logger.debug("The email of user to add: " + user.getEmail());
//		//checking in user already exists
//		User registered = createUserAccount(user);
//		if (registered == null) {
			//creating new User and saving it to Database
			userService.createProfile(user);
			return new ModelAndView("home");
//			logger.debug("The email of createProfile: " + user.getEmail());
//			return new ModelAndView("home");
//		} else {
//			logger.debug("The email of existing User: " + user.getEmail());
//			ModelAndView model = new ModelAndView();
//			model.addObject("emailExists", "The profile already exists");
//			model.setViewName("signinup");
//			return model;
//		}
	}

	private User createUserAccount(User user){
		User registered = null;
		registered = userService.findByEmail(user.getEmail());
		return registered;
	}
}
