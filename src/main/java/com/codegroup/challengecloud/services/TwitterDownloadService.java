package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.model.User;

import org.apache.log4j.Logger;
import org.hibernate.hql.internal.ast.tree.MapEntryNode;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
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
    private UsersConnectionRepository usersConnectionRepository;
    
    @Resource
    private PostService postService;
    
    @Resource
    private UserService userService;
    
    @Resource
    private OriginService originService;

    @Resource
    private SubscriptionService subscriptionService;

    @Inject
    public TwitterDownloadService(Twitter twitter, ConnectionRepository connectionRepository, UsersConnectionRepository usersConnectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
        this.usersConnectionRepository = usersConnectionRepository;
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
            for (HashTagEntity hashTagEntity : currentTweet.getEntities().getHashTags()) {
                if (hashTagEntity.getText().equals(hash)) {
                    suitableTweets.add(currentTweet);
                }
            }
        }
        log.info("downloadUserHashedPosts() stop");
        return suitableTweets;
    }
    
    public Set<Tweet> downloadUserTweets(String providerUserId) {
        log.info("downloadUserTweets(" + providerUserId + ") start");
        // BE VERY_VERY CAREFULL WITH getUserTimeline(long, AMOUNT_OF_POSTS);
        List<Tweet> userTweets = twitter.timelineOperations().getUserTimeline(Long.parseLong(providerUserId), AMOUNT_OF_POSTS);
        Set<Tweet> setUserTweets = new HashSet<Tweet>(userTweets);
        log.info("downloadUserTweets(" + providerUserId + ") stop");
        return setUserTweets;
    }
    
    public String addUserHashedPosts(String userId) {
    	// Get ProviderUserId
    	log.debug("addUserHashedPosts");
    	ConnectionRepository userConnectionRepository = usersConnectionRepository.createConnectionRepository(userId);
    	log.debug("userConnectionRepository - success");
    	List<Connection<?>> possibleUserConnections = userConnectionRepository.findConnections("twitter");
    	log.debug("possibleUserConnections.size() = " + possibleUserConnections.size());
    	Connection userConnection = possibleUserConnections.get(0);
    	log.debug("userConnection - success");
    	String providerUserId = userConnection.getKey().getProviderUserId();
    	//return providerUserId;
    	Integer postsAdded = new Integer(0);
    	// Get all subscriptions
    	List<Subscription> userSubscriptions = subscriptionService.findByUserId(userId);
    	Set<String> postsHashes = new HashSet();
    	// Get Set of hashes (tags) in new user posts
	Set<Tweet> userTweets = downloadUserTweets(providerUserId);
//List<Tweet> listUserTweets = twitter.timelineOperations().getUserTimeline(userId, AMOUNT_OF_POSTS);
    	log.debug("Line 102");
//List<Tweet> listUserTweets =  ((Twitter)(userConnection.getApi())).timelineOperations().getUserTimeline(userId, AMOUNT_OF_POSTS);
//log.debug("Line 103");
//Set<Tweet> userTweets = new HashSet<Tweet>(listUserTweets);
    	log.debug("userTweets.size() = " + userTweets.size());
    	for (Tweet userTweet : userTweets) {
    		for (HashTagEntity tweetHashTagEntity : userTweet.getEntities().getHashTags()) {
    			postsHashes.add(tweetHashTagEntity.getText());
    		}
    	}
    	log.debug("postsHashes.size() = " + postsHashes.size());
    	log.debug("userSubscriptions.size() = " + userSubscriptions.size());
    	// Get 1 origin for Twitter
    	com.codegroup.challengecloud.model.Origin twitterOrigin = originService.findById(TWITTER_ORIGIN_ID);
    	// For each subscription test if it's hash is in set. If is, add  an entity to Posts table.
		    	//TODO May be, we'd better use map<String, Subscription> with posts hashes and Subscriptions???
		    	// Firstly we will fill the map with only Subscriptions Hashes
		    	// And then, we will try to get suitable subscription from this map for each Tweet. If it has, add an Entity
    	for (Subscription userSubscription : userSubscriptions) {
    		String challengeHash = userSubscription.getChallenge().getHashtag();
    		if (postsHashes.contains(challengeHash)) {
    			// This means, that for that subscription there are some posts in userTweets
    	    	for (Tweet userTweet : userTweets) {
    	    		boolean tweetContainsHash = false;
    	    		for (HashTagEntity hte : userTweet.getEntities().getHashTags()) {
    	    			if (hte.getText().equals(challengeHash)) {
    	    				tweetContainsHash = true;
    	    			}
    	    		}
    	    		log.debug("tweet with text \"" +
	    	    		userTweet.getText() +
	    	    		"\" contains hash " +
	    	    		challengeHash + " is " +
	    	    		Boolean.toString(tweetContainsHash));
    	    		if (tweetContainsHash) {
    	    			// So, this Tweet is suitable for our subscription. If there is no one in DB, let's add!!!
    	    			//TODO Check, if there is THIS tweet in DB, it could be done earlier, by tweet date, id
    	    			postService.createPost(userSubscription,
    	    					//new java.sql.Date((new java.util.Date()).getTime()),
    	    					new java.sql.Date(userTweet.getCreatedAt().getTime()),
    	    					twitterOrigin);
    	    			postsAdded += 1;
    	    	    	log.debug("Almost added " + postsAdded + " posts");
    	    		} else {
    	    			//log.debug("userTweet with hash");
    	    		}
    	    	}
    		}
    	}
    	return "Added" + postsAdded + " posts into tabe";
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
        for (Post tempPost : postsFromSubscription) {
                /*TODO if will be more providers, add there here*/
            if (tempPost.getOrigin().getId().equals(TWITTER_ORIGIN_ID)) {

                log.debug("Origin name and id are " + tempPost.getOrigin().getName() + " " + tempPost.getOrigin().getId());
                tweetsFromSubscription.add(twitter.timelineOperations().getStatus(Long.parseLong(tempPost.getId())));
            }
        }
        return tweetsFromSubscription;
    }
}
