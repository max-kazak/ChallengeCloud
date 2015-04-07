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
    
    /* Created on 04.04.2015 by Vladimr Zhdanov */
    public Set<Tweet> downloadUserTweets(String providerUserId) {
        log.info("downloadUserTweets(" + providerUserId + ") start");
        // BE VERY_VERY CAREFULL WITH getUserTimeline(long, AMOUNT_OF_POSTS);
        List<Tweet> userTweets = twitter.timelineOperations().getUserTimeline(Long.parseLong(providerUserId), AMOUNT_OF_POSTS);
        Set<Tweet> setUserTweets = new HashSet<Tweet>(userTweets);
        log.info("downloadUserTweets(" + providerUserId + ") stop");
        return setUserTweets;
    }

    /* Created on 04.04.2015 by Vladimr Zhdanov */
    public int addUserHashedPosts(String userId) {
    	// Get ProviderUserId
    	log.debug("addUserHashedPosts");
    	ConnectionRepository userConnectionRepository = usersConnectionRepository.createConnectionRepository(userId);
    	log.debug("userConnectionRepository - success");
    	List<Connection<?>> possibleUserConnections = userConnectionRepository.findConnections("twitter");
    	log.debug("possibleUserConnections.size() = " + possibleUserConnections.size());
    	if (possibleUserConnections.size() == 0) {
    		return 0;
    	}
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
		// Put all user subscriptons into Map<String subsHashTag, SUbscription>
		Map<String, Subscription> subscMap = new HashMap<String, Subscription>();
		for (Subscription userSubsc : userSubscriptions) {
			subscMap.put(userSubsc.getChallenge().getHashtag(), userSubsc);
		}
    	log.debug("subscMap.size() = " + subscMap.size());
    	// Get 1 origin for Twitter
    	com.codegroup.challengecloud.model.Origin twitterOrigin = originService.findById(TWITTER_ORIGIN_ID);
    	// For each userTweet test if either one of it's hashes is in Map.
    	// If is, check, if this post is already in table and add, if not
    	for (Tweet currentUserTweet : userTweets) {
    		for (HashTagEntity hte : currentUserTweet.getEntities().getHashTags()) {
    			Subscription currentUserSubscription;
    			if ((currentUserSubscription = subscMap.get(hte.getText())) != null) {
    				String currentUserTweetId = Long.toString(currentUserTweet.getId());
    				log.debug("Found tweet with hashTag for one of userSubscriptions");
    				log.debug("currentUserTweetId = " + currentUserTweetId);
    				if (postService.findById(currentUserTweetId) == null) {
        				log.debug("There is no post with such id currentUserTweetId = " + currentUserTweetId);
    					// Put new Post into table
    	    			postService.createPost(currentUserTweetId,
    	    					currentUserSubscription,
    	    					//new java.sql.Date((new java.util.Date()).getTime()),
    	    					new java.sql.Date(currentUserTweet.getCreatedAt().getTime()),
    	    					twitterOrigin);
    	    			postsAdded += 1;
    				}
    				// What to do, if there are many suitable hash tags?
    				break;
    			}
    		}
    	}
    	return postsAdded;
    }
    
    public int addAllUsersTwitterPosts() {
    	//TODO rewrite these mathods to increase perfomanse of using Spring Social
    	log.debug("addAllUsersTwitterPosts started. Look for all users ");
    	List<User> allChallengeUsers = userService.findAll();
    	log.info("addAllUsersTwitterPosts for " + allChallengeUsers.size() + " users");
    	int addedPosts = 0;
    	for (User currentUser : allChallengeUsers) {
    		// I think, there is no big differense either to give ID or User, because we do not need User entity
    		// We look for all user subscriptions by user's id
    		addedPosts = addedPosts + addUserHashedPosts(currentUser.getId());
    	}
    	return addedPosts;
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
