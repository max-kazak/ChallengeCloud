package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.constants.EventIds;
import com.codegroup.challengecloud.dao.BadgeDao;
import com.codegroup.challengecloud.dao.HistoryDao;
import com.codegroup.challengecloud.model.Badge;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.History;
import com.codegroup.challengecloud.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by Krokhin on 21.04.2015.
 */
@Repository("historyDao")
public class HistoryDaoMySQL extends HibernateDao implements HistoryDao {

    private static final Logger log = Logger.getLogger(HistoryDaoMySQL.class);

    @Autowired
    BadgeDao badgeDao;

    @Override
    public void save(History history) {
        log.debug("saving history record eventID " + history.getEvent().getId() + ", time " +
                history.getTimestamp() + ", userID" + history.getUser().getId());
        getSession().save(history);
    }

    @Override
    public void update(History history) {
        log.debug("updating history record eventID " + history.getEvent().getId() + ", time " +
                history.getTimestamp() + ", userID" + history.getUser().getId());
        getSession().update(history);
    }

    @Override
    public void delete(History history) {
        log.debug("removing history record eventID " + history.getEvent().getId() + ", time " +
                history.getTimestamp() + ", userID" + history.getUser().getId());
        getSession().delete(history);
    }

    @Override
    public History findByRefId(String refId) {
        log.debug("find history record with refID " + refId);
        List list = find("from History where REF_ID = ?", refId);
        return (History) list.get(0);
    }

    @Override
    public History findById(String historyId) {
        log.debug("find history record by PK:  " + historyId);
        List list = find("from History where HISTORY_ID = ?", historyId);
        return (History) list.get(0);
    }

    @Override
    public Set<History> getHistoryForUser(User user) {
        Set<History> unsortedHistory = user.getHistoryNotes();
        Set<History> sortedHistory = new TreeSet<>(new Comparator<History>() {
            @Override
            public int compare(History o1, History o2) {
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }

        });

        for (History tempHistory : unsortedHistory) {
            sortedHistory.add(tempHistory);
        }
        return sortedHistory;
    }

    @Override
    public long getNumberOfTwitterPosts(User user) {
        long num;
        Query query = getSession().createQuery(
                "select count(*) from com.codegroup.challengecloud.model.History history " +
                "WHERE event_id = ? AND user_id = ?")
                .setString(0, EventIds.TWITTERPOSTEVENT_ID)
                .setString(1, user.getId());
        num = (long) query.list().get(0);
        log.debug("Number of UserTweets with id " + user.getId() + " equals " + num);
        return num;
    }

    @Override
    public Set<Badge> getUserBadges(User user) {
        log.debug("Getting all badges of user with id = " + user.getId());
        List list = find("from History where EVENT_ID = ?", EventIds.ACHIEVMENTEVENT_ID);
        log.debug("list of badges returned: " + list.toString());
        Set<Badge> badges = new HashSet<>();
        for (Object temp : list) {
            if (temp instanceof History) {
                badges.add(badgeDao.findById(((History) temp).getRefId()));
            }
        }
        return badges;
    }

    @Override
    public long getNumberOfTwitsForUserByChallenge(User user, Challenge challenge) {
        SQLQuery query = getSession()
                .createSQLQuery("SELECT COUNT(*) " +
                                "FROM challenger.history ch, challenger.posts p, challenger.subscriptions s " +
                                "WHERE ch.REF_ID = p.POST_ID AND p.SUBSCRIPTION_ID = s.SUBSCRIPTION_ID " +
                                "AND s.CHALLENGE_ID = :challenge_id " +
                                "AND ch.USER_ID = :user_id " +
                                "GROUP BY ch.USER_ID");
        query.setString("challenge_id", challenge.getId());
        query.setString("user_id", user.getId());
        long number = ((BigInteger) query.list().get(0)).longValue();
        log.debug("Number of tweets for user = " + user.getId() +
                " with challenge_id = " + challenge.getId() +
                " is " + number);
        return number;
    }

    @Override
    public long getNumberOfCompletedChallenges(User user, Challenge challenge) {
        SQLQuery query = getSession()
                .createSQLQuery("SELECT COUNT(*) " +
                                "FROM challenger.history ch " +
                                "WHERE ch.REF_ID = :challenge_id " +
                                "AND ch.USER_ID = :user_id " +
                                "GROUP BY ch.USER_ID");
        query.setString("challenge_id", challenge.getId());
        query.setString("user_id", user.getId());
        long number = ((BigInteger) query.list().get(0)).longValue();
        log.debug("Number of completed challenges for user = " + user.getId() +
                " with challenge_id = " + challenge.getId() +
                " is " + number);
        return number;
    }
}
