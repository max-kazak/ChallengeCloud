package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Subscription;

import java.sql.Date;
import java.util.List;


/**
 * Created by Johnny D on 23.02.2015.
 */
public interface SubscriptionDao {
    public void save(Subscription subscription);
    public void update(Subscription subscription);
    public void delete(Subscription subscription);

    public Subscription findById(String subscriptionId);
}
