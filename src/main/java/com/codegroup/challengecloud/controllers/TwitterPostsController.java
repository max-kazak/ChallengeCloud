package com.codegroup.challengecloud.controllers;

import java.util.HashMap;
import java.util.Map;

import com.codegroup.challengecloud.services.TwitterDownloadService;
import com.codegroup.challengecloud.services.UserService;

import org.apache.log4j.Logger;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @author Vladimir Zhdanov
 *         Created 22.03.2015
 */

@Controller
public class TwitterPostsController {
    private static final Logger log = Logger.getLogger(TwitterPostsController.class);
    @Resource
    private UserService userService;

//    @Resource
    private Twitter twitter;
    
    @Resource
    private TwitterDownloadService twitterDownloadService;

    @Inject
    public TwitterPostsController (Twitter twitter) {
    	this.twitter = twitter;
    	log.info("TwitterPostsController created");
    }
    
    @RequestMapping("/twitter-posts")
    public ModelAndView twitterPostsText() {
    	Map <String,String> map = new HashMap();
        return new ModelAndView("twitter-posts", map);
    }

}