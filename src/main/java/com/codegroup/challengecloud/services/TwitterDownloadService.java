package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import org.apache.log4j.Logger;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

import java.util.*;

/**
 * Created by Yefim on 19.03.2015.
 */
@Service("twitterDownloadService")
public class TwitterDownloadService {
    private static final Logger log = Logger.getLogger(TwitterDownloadService.class);
    private static final int AMOUNT_OF_POSTS = 255;
    private static final String TWITTER_ORIGIN_ID = "twit_id";
    private Twitter twitter;
    private ConnectionRepository connectionRepository;

    @Resource
    private SubscriptionService subscriptionService;

    @Inject
    public TwitterDownloadService(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    /**
     * LastModified on 29.03.2015 by Vladimir Zhdanov
     *
     * @param userId
     * @param hash   A hash tag without '#' character
     * @return
     */
    public Set<Tweet> downloadUserHashedPosts(long userId, String hash) {
        //TODO try catch
        log.info("downloadUserHashedPosts() start");
        List<Tweet> userTweets = twitter.timelineOperations().getUserTimeline(userId, AMOUNT_OF_POSTS);
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
        log.info("downloadUserHashedPosts() stop");
        return suitableTweets;
    }

    /**
     * @return Set of tweets to show them on subscription page
     * @author Yefim Krokhin on 31.03.2015
     */

    public Set<Tweet> downloadTweetsForSubscriptionPage(String subscriptionId) {
        Subscription subscription = subscriptionService.findById(subscriptionId);
        Set<Post> postsFromSubscription = subscription.getPosts();
        Set<Tweet> tweetsFromSubscription = new TreeSet<>(new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                return o1.getCreatedAt().compareTo(o2.getCreatedAt());
            }
        });
        if (postsFromSubscription != null) {
            for (Post tempPost : postsFromSubscription) {
                /*TODO if will be more providers, add there here*/
                if (tempPost.getOrigin().getId().equals(TWITTER_ORIGIN_ID)) {

                    log.debug("Origin name and id are " + tempPost.getOrigin().getName() + " " + tempPost.getOrigin().getId());
                    tweetsFromSubscription.add(twitter.timelineOperations().getStatus(Long.parseLong(tempPost.getId())));
                }
            }
            return tweetsFromSubscription;
        } else {
            return null;
        }
    }
}
