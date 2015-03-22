package com.codegroup.challengecloud.controllers;

import java.util.HashMap;
import java.util.Map;

import com.codegroup.challengecloud.services.TwitterDownloadService;
import com.codegroup.challengecloud.services.UserService;

import org.apache.log4j.Logger;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Vladimir Zhdanov
 *         Created 22.03.2015
 */

@Controller
public class TwitterPostsController {
    private static final Logger log = Logger.getLogger(ChallengesController.class);
    @Resource
    private UserService userService;
    
    @Resource
    private TwitterDownloadService twitterDownloadService;

    @RequestMapping("/twitter-posts")
    public ModelAndView ctwitterPostsText() {
    	Map <String,String> map = new HashMap();
    	map.put("message", "Friends"); 
        return new ModelAndView("twitter-posts", map);
    }

    /**
     * If not params given - returns page with several fields and buttons.
     * If specified id and get=='posts' - returns String with posts of that user, fileterd by hash, if given.
     * If specified id and get=='friends' - returns String with friend of that user. 
     * @param id TwitterUserID
     * @param get What to get friends of posts
     * @param hash hashtag (optionally)
     * @return Page
     */
    @RequestMapping(value = "/twitter", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTwitterPostsPage(
    		@RequestParam(value = "id", required = false) String id,//user id
            @RequestParam(value = "get", required = false) String get,// whether to get user friends of=r posts
            @RequestParam(value = "hash", required = false) String hash) {
        log.info("getTwitterPostsPage()");
        String code = "<p> Internal Error! </p>";
        if (id != null) {
        	if (get.equals("friends")) {
        		return "<p> First friend </p>";
        	} else if (get.equals("posts")) {
        		SearchResults searchResuts = twitterDownloadService.downloadPosts();
        		//return ":-)";
        		return searchResuts.toString() + ":)";
        		/*if (hash != null) {
        			return "22";
        		} else {
        			return "12";
        		}*/
        	} else {
        		return "Strange";
        	}
        	
        } else {
        	return "Null";
        }
    }
}