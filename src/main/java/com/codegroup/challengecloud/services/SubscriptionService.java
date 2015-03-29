package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.SubscriptionDao;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.utils.Generator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Nipel-Crumple on 23.02.2015.
 */
@Service("subscriptionService")
public class SubscriptionService {
    @Autowired
    SubscriptionDao subscriptionDao;
    
    private static final Logger log = Logger.getLogger(SubscriptionService.class);
    
    public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    @Transactional
    public Subscription createSubscription(User user, Challenge challenge, Date date, Set<Post> posts) {
        Subscription subscription = new Subscription();

        subscription.setId(Generator.generateId());
        subscription.setUser(user);
        subscription.setPosts(posts);
        subscription.setChallenge(challenge);
        subscription.setDate(date);

        subscriptionDao.save(subscription);

        return subscription;
    }

    @Transactional
    public Subscription findById(String subscriptionId) {
        return subscriptionDao.findById(subscriptionId);
    }
    
    @Transactional
    public List<Subscription> findByUserId(String userId) {
        return subscriptionDao.findByUserId(userId);
    }
    
    /**
     * @return List of subscriptions of the user, that is currently logged in
     * @author Created by Andrey on 29.03 2015
     */
    @Transactional
    public List<Subscription> findForCurrentUser() {
    	UserService userService = new UserService();
    	String userId = userService.getCurrentUser().getId();
    	log.debug("Got userId: "+userId+". About to get subscriptions");
    	return subscriptionDao.findByUserId(userId);
    }
}
