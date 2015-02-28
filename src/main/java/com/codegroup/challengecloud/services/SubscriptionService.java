package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.SubscriptionDao;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Set;

/**
 * Created by Nipel-Crumple on 23.02.2015.
 */
@Service("subscriptionService")
public class SubscriptionService {
    @Autowired
    SubscriptionDao subscriptionDao;

    public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

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
}
