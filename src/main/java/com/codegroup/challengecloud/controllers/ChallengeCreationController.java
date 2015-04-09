package com.codegroup.challengecloud.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codegroup.challengecloud.services.ChallengeService;

@Controller
public class ChallengeCreationController {
	private static final Logger log = Logger.getLogger(ChallengeCreationController.class);
	
    @Resource
    private ChallengeService challengeService;
    
    @RequestMapping("/challenge_creation")
    public
    @ResponseBody
    ModelAndView challengeCreation() {
    	log.info("Returning defaul page for challenge creation");
    	return new ModelAndView("challenge_creation");
    }
    
    @RequestMapping("/create_challenge")
    public
    @ResponseBody
    String challengeCreation(
    		@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "description", required = true) String description,
    		@RequestParam(value = "diff", required = true) Integer difficulty,
    		@RequestParam(value = "hash", required = true) String hashtag) {
    	log.info("Creating a challenge with parameters: "
    			+ "\n Title: " + title
    			+ "\n Description: " + description
    			+ "\n Difficulty:" + difficulty
    			+ "\n Hashtag: " +hashtag);
    	try {
    		challengeService.createChallenge(title, description, difficulty, hashtag);
    		return "Success!";
    	} catch(Exception e) {
    		log.error("Ann error occured",e);
    		return "Failed to create a challenge";
    	}
    }
}
