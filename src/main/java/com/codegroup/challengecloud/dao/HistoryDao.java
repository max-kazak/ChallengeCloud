package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Krokhin on 19.04.2015.
 */
@Repository("historyDao")
public interface HistoryDao {
    public void save(History history);

    public void update(History history);

    public void delete(History history);

    public History findByRefId(String refId);

    public History findById(String historyId);

    public Set<History> getHistoryForUser(User user);

    public long getNumberOfTwitterPosts(User user);

    public Set<Badge> getUserBadges(User user);

    public long getNumberOfTwitsForUserBySubscription(User user, Subscription subscription);

    public long getNumberOfCompletedChallenges(User user, Challenge challenge);
}
