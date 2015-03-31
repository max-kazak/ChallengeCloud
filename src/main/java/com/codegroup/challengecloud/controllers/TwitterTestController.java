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
    	return new ModelAndView("testpage");
    }
    
    @RequestMapping(value = "/testpage_postcomment", method = RequestMethod.GET)
    public
    @ResponseBody
    void postComment() {
    	twitterCommentService.postComment(582855924873883648L, "Posting testing");
    }
}
