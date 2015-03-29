package com.codegroup.challengecloud.services;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yefim on 19.03.2015.
 */
@Service("twitterDownloadService")
public class TwitterDownloadService {
    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Inject
    public TwitterDownloadService(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    /**
     * LastModified on 29.03.2015 by Vladimir Zhdanov
     * @param userId
     * @param hash A hash tag without '#' character
     * @return
     */
    public Set<Tweet> downloadUserHashedPosts(long userId, String hash) {
    	//TODO try catch
		List<Tweet> userTweets = twitter.timelineOperations().getUserTimeline( userId , 255);
    	Set<Tweet> suitableTweets = new HashSet<Tweet>();
        for (Tweet currentTweet : userTweets) {
        	//if (!currentTweet.getText().contains(hash.subSequence(0, hash.length())) ) {
        	//if (currentTweet.getEntities().getHashTags().contains(new HashTagEntity(text, indices)))
        	for (HashTagEntity hashTagEntity : currentTweet.getEntities().getHashTags()) {
        		if (hashTagEntity.getText().equals(hash)) {
        			suitableTweets.add(currentTweet);
        		}
        	}
        }
        return suitableTweets;
    }
}
