package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.constants.EventIds;
import com.codegroup.challengecloud.dao.BadgeDao;
import com.codegroup.challengecloud.dao.PostDao;
import com.codegroup.challengecloud.dao.impl.BadgeDaoMySQL;
import com.codegroup.challengecloud.model.*;
import com.codegroup.challengecloud.services.events.AchievementEvent;
import com.codegroup.challengecloud.services.events.CCloudEvent;
import com.codegroup.challengecloud.services.events.ChallengeCompletedEvent;
import com.codegroup.challengecloud.services.events.TwitterPostEvent;
import com.codegroup.challengecloud.utils.Generator;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by Andrey on 15.03.2015.
 */
@Service("postService")
public class PostService {
    private static final Logger log = Logger.getLogger(PostService.class);
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    PostDao postDao;
    @Autowired
    HistoryService historyService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private BadgeService badgeService;

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Transactional
    public Post createPost(Subscription subscription, Date date, Origin origin) {
        Post post = new Post();

        post.setId(Generator.generateId());
        post.setSubscription(subscription);
        post.setDate(date);
        post.setOrigin(origin);

        postDao.save(post);

        return post;
    }

    /**
     * created on 07.04.2015 by Vladimir Zhdanov
     *
     * @param postId       id for new post
     * @param subscription
     * @param date
     * @param origin
     * @return
     */
    @Transactional
    public Post createPost(String postId, Subscription subscription, Date date, Origin origin) {
        Post post = new Post();
        post.setId(postId);
        post.setSubscription(subscription);
        post.setDate(date);
        post.setOrigin(origin);
        postDao.save(post);
        User user = subscription.getUser();
        Challenge challenge = subscription.getChallenge();
        CCloudEvent event = new TwitterPostEvent(applicationContext,
                "create new Post with origin " + origin.getName(),
                user,
                date.getTime(),
                postId);
        applicationContext.publishEvent(event);
        log.info(event.toString());

        long conditionTweets = 0;
        long numOfTweets = historyService.getNumberOfTweetsForUserByChallenge(user, challenge);

        List<Badge> badges = badgeService.findByEventId("4");
        Badge neededBadge = null;
        for (Badge badge : badges) {
            try {
                JSONObject json = new JSONObject(badge.getCondition());
                if (json.getString("challenge_id").equals(challenge.getId())) {
                    conditionTweets = json.getLong("posts");
                    neededBadge = badge;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (numOfTweets % conditionTweets == 0) {
            log.debug("Number of tweets. Actual: " + numOfTweets + " ;\n Condition: " + conditionTweets);
            ChallengeCompletedEvent eventCompleted = new ChallengeCompletedEvent(applicationContext,
                    "Challenge with id = " + challenge.getId() + " completed by userId = " + user.getId(),
                    user, date.getTime(), challenge);
            AchievementEvent achievementEvent = new AchievementEvent(applicationContext, "Achievement with challengeId = " +
            challenge.getId() + " completed by userId = " + user.getId(), user, date.getTime(), neededBadge);

            applicationContext.publishEvent(eventCompleted);
            applicationContext.publishEvent(achievementEvent);
            log.info(event.toString());
        }

        return post;
    }

    @Transactional
    public Post findById(String postId) {
        return postDao.findById(postId);
    }

    @Transactional
    public List<Post> findPostsByUserSubscriptions() {
        return postDao.findPostsByUserSubscriptions(getUserSubscriptions());
    }

    private List<Subscription> getUserSubscriptions() {
        return subscriptionService.findByUserId(userService.getCurrentUser().getId());
    }
}
