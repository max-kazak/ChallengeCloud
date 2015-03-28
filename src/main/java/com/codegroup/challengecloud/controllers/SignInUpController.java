package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.UserService;
import com.codegroup.challengecloud.services.security.ChallengerUserDetailsService;
import com.codegroup.challengecloud.services.social.ConnectionSignUpAdapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;

@Controller

public class SignInUpController {

	@Autowired
	ConnectionSignUpAdapter signUpAdapter;

	private static Logger logger = Logger.getLogger(SignInUpController.class);
	@Autowired
	UserService userService;

	@Autowired
	ChallengerUserDetailsService userDetailsService;

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
	@RequestMapping(value="/signinup", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, Model model) {
		logger.debug("The email of user to add: " + user.getEmail());

		//checking in user already exists
		UserDetails registered = checkIfUserExists(user);
		if (registered == null) {

			//creating new User and saving it to Database
			userService.createProfile(user);
			UserDetails userDetails = userDetailsService.loadUserByEmail(user.getEmail());
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());

			if (auth.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(auth);
				logger.debug("Registration and authentication was successful, user with email: " + user.getEmail());
			} else {
				logger.debug("Unsuccessful registration, no rights for user with email: " + user.getEmail());
			}
			return "redirect:/home";
		} else {
			logger.debug("The email of existing User: " + user.getEmail());
			model.addAttribute("emailExists", "The profile already exists with such email: " + user.getEmail());
			return "signinup";
		}
	}

	private UserDetails checkIfUserExists(User user){
		logger.debug("Finding User with email in SignInUpController: " + user.getEmail());
		try {
			UserDetails registered = userDetailsService.loadUserByEmail(user.getEmail());
			if (registered != null) {
				logger.debug("The user has already exists with username: " + registered.getUsername());
			}

			return registered;
		} catch (IndexOutOfBoundsException e) {
			logger.debug("There is no user in database with email: " + user.getEmail());
			return null;
		} catch (NullPointerException e1) {
			logger.debug("No user with email: " + user.getEmail());
			return null;
		}
	}
}
