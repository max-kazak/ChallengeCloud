package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.SubscriptionDao;
import com.codegroup.challengecloud.model.Subscription;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Nipel-Crumple on 23.02.2015.
 */
@Repository("subscriptionDao")
public class SubscriptionDaoMySQL extends HibernateDao implements SubscriptionDao {

    private static final Logger log = Logger.getLogger(SubscriptionDaoMySQL.class);

    @Override
    public void save(Subscription subscription) {
        log.debug("saving subscription " + subscription.getId());
        getSession().save(subscription);
    }

    @Override
    public void update(Subscription subscription) {
        log.debug("updating subscription " + subscription.getId());
        getSession().update(subscription);
    }

    @Override
    public void delete(Subscription subscription) {
        log.debug("deleting subscription " + subscription.getId());
        getSession().delete(subscription);
    }

    @Override
    public Subscription findById(String subscriptionId) {
        log.debug("finding subscription by ID " + subscriptionId);
        List list = find("from Subscription where " +
                "SUBSCRIPTION_ID = ?", subscriptionId);
        return (Subscription) list.get(0);
    }

}
