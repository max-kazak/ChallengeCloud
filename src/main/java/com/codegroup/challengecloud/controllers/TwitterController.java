package com.codegroup.challengecloud.controllers;

import java.util.Set;

import com.codegroup.challengecloud.services.TwitterDownloadService;
import com.codegroup.challengecloud.services.UserService;

import org.apache.log4j.Logger;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Vladimir Zhdanov
 *         Created 22.03.2015
 */

@Controller
public class TwitterController {
    private static final Logger log = Logger.getLogger(TwitterController.class);
    @Resource
    private UserService userService;

    @Resource
    private TwitterDownloadService twitterDownloadService;

    /**
     * If not params given - returns page with several fields and buttons.
     * If specified id and get=='posts' - returns String with posts of that user, fileterd by hash, if given.
     * If specified id and get=='friends' - returns String with friend of that user. 
     * @param id TwitterUserID
     * @param hash hash tag (optionally)
     * @return Page
     */
    @RequestMapping(value = "/twitter", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTwitterPosts(
    		@RequestParam(value = "id", required = false) String id,//user id
            @RequestParam(value = "hash", required = false) String hash,
            @RequestParam(value = "add", required = false) Boolean add) {
        if ((id != null) && (hash != null)) {
        	/* Get tweets from user and filter them by hashtag */
        	log.info("getTwitterPosts() id = "+id+" hash = "+hash);
            String res = "";
        	if (add!=null && add.booleanValue()) {
        		//return "Added" + twitterDownloadService.addUserHashedPosts(id)  + " posts into tabe";
        		return "Added" + twitterDownloadService.addAllUsersTwitterPosts()  + " posts into tabe";
        	} else {
	        	Set<Tweet> userTweets = twitterDownloadService.downloadUserHashedPosts(Long.parseLong(id), hash);
	            log.info("getTwitterPosts() success");
	            /* Show tweets on page */
	            for (Tweet currentTweet : userTweets) {
	            		res += currentTweet.getText() + "<br/>";
	            }
        	}
    		return res;
        } else {
            log.info("getTwitterPosts() incorrect input");
        	return "Incorrect request";
        }
    }
}