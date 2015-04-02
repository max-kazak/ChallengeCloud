package com.codegroup.challengecloud.services;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TweetData;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import com.codegroup.challengecloud.config.SocialConfig;
import com.codegroup.challengecloud.controllers.HomeController;

@Service("twitterCommentService")
public class TwitterCommentService {
	
    private Twitter twitter;
    private ConnectionRepository connectionRepository;
    
    private static final Logger log = Logger.getLogger(TwitterCommentService.class);
    
    @Inject
    public TwitterCommentService(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    /**
     * This method posts a tweet
     * @param message Message to post
     * @author Andrey 31.03.2015
     */
    public void postTweet(String message) {
    	log.debug("Posting a tweet");
    	twitter.timelineOperations().updateStatus(message);
    	log.debug("Succesfully posted: " + message);
    }
    
    /**
     * This a test method to check whether id is valid
     * P.S. It is
     */
    public Tweet getTweet(long id) {
    	log.debug("Getting a tweet");
    	Tweet result = twitter.timelineOperations().getStatus(id);
    	log.debug("Succesfully got: " + id);
    	return result;
    }
    
    //582859494604050432L - use this for tests
    /**
     * This method comments an existing tweet
     * @param tweetId Tweet that is commented
     * @param message Message to post
     * @author Andrey 31.03.2015
     */
    public void postComment(long tweetId,String message) {
    		log.debug("Posting a comment");
	    	TweetData tweetData = new TweetData(message).inReplyToStatus(tweetId);
	    	twitter.timelineOperations().updateStatus(tweetData);
	    	log.debug("Succesfully posted: \""+ message + "\" in reply to " + tweetId);
    }
}
