package com.codegroup.challengecloud.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("message", name);
		return "welcome";
 
	}
	
}