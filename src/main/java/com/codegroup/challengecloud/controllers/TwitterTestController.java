package com.codegroup.challengecloud.controllers;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codegroup.challengecloud.services.TwitterCommentService;

@Controller
public class TwitterTestController {
    private static final Logger log = Logger.getLogger(TwitterTestController.class);
    @Resource
    private TwitterCommentService twitterCommentService;

    /**
     * Test
     */
    @RequestMapping(value = "/testpage", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView testpage() {
    	log.debug("Test controller has been found");
    	return new ModelAndView("testpage");
    }
    
    @RequestMapping(value = "/testpage_postcomment", method = RequestMethod.GET)
    public
    @ResponseBody
    String postComment() {
    	log.debug("Trying to call posting method");
    	String res;
	    try{
    		twitterCommentService.postComment(582859494604050432L, "Testing posting");
	    	res = "Succesfully posted";
	    	log.debug("Success!");
	    } catch(Exception e) {
	    	res = "Error";
	    	log.debug("No success...",e);
	    }
    	return res;
    }
    
    @RequestMapping(value = "/testpage_getcomment", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTweet() {
    	log.debug("Trying to call getting method");
    	String res;
	    try{
    		res = twitterCommentService.getTweet(582859494604050432L).getText();
	    	log.debug("Success!");
	    } catch(Exception e) {
	    	res = "Error";
	    	log.debug("No success...",e);
	    }
    	return res;
    }
}
