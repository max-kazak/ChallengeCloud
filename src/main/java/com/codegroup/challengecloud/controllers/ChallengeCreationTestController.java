package com.codegroup.challengecloud.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codegroup.challengecloud.model.Image;
import com.codegroup.challengecloud.services.ChallengeService;
import com.codegroup.challengecloud.services.ImageService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * Created on 09.04.2015
 * @author Andrey
 */
@Controller
public class ChallengeCreationTestController {
	private static final Logger log = Logger.getLogger(ChallengeCreationTestController.class);
	
    @Resource
    private ChallengeService challengeService;
    @Resource
    private ImageService imageService;
    
    @RequestMapping("/challenge_creation_test")
    public
    @ResponseBody
    ModelAndView challengeCreation() {
    	log.info("Returning default page for test challenge creation.");
    	return new ModelAndView("challenge_creation_test");
    }
    
    @RequestMapping("/create_challenge_test")
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
    		log.error("An error occured",e);
    		return "Failed to create a challenge";
    	}
    }
}

