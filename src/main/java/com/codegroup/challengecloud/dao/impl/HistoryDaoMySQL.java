package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.constants.EventIds;
import com.codegroup.challengecloud.dao.HistoryDao;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.History;
import com.codegroup.challengecloud.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Krokhin on 21.04.2015.
 */
@Repository("historyDao")
public class HistoryDaoMySQL extends HibernateDao implements HistoryDao {

    private static final Logger log = Logger.getLogger(HistoryDaoMySQL.class);

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


}
