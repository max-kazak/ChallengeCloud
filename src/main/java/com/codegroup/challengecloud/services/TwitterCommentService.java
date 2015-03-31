package com.codegroup.challengecloud.services;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TweetData;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

@Service("twitterCommentService")
public class TwitterCommentService {
    private Twitter twitter;
    private ConnectionRepository connectionRepository;

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
    	twitter.timelineOperations().updateStatus(message);
    }
    
    //582855924873883648L - use this for tests
    /**
     * This method comments an existing tweet
     * @param tweetId Tweet that is commented
     * @param message Message to post
     * @author Andrey 31.03.2015
     */
    public void postComment(long tweetId,String message) {
    	TweetData tweetData = new TweetData(message).inReplyToStatus(tweetId);
    	twitter.timelineOperations().updateStatus(tweetData);
    }
}
