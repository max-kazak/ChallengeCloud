package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.HistoryDao;
import com.codegroup.challengecloud.model.History;
import org.apache.log4j.Logger;

import java.sql.Timestamp;

/**
 * Created by Krokhin on 21.04.2015.
 */
public class HistotyDaoMySQL extends HibernateDao implements HistoryDao {

    private static final Logger log = Logger.getLogger(HistotyDaoMySQL.class);

    @Override
    public void save(History history) {
        log.debug("saving history record eventID " + history.getEventId() + ", time " +
                history.getTimestamp() + ", userID" + history.getUserId());
    }

    @Override
    public void update(History history) {
        log.debug("updating history record eventID " + history.getEventId() + ", time " +
                history.getTimestamp() + ", userID" + history.getUserId());
    }

    @Override
    public void delete(History history) {
        log.debug("removing history record eventID " + history.getEventId() + ", time " +
                history.getTimestamp() + ", userID" + history.getUserId());
    }

    @Override
    public History findByRefId(String refId) {
        log.debug("find history record with reID " + refId);
        return null;
    }

    @Override
    public History findByCompositePK(String userId, Timestamp timestamp, String eventId) {
        log.debug("find history record by composite PK: userID " + userId + ", time " + timestamp +
                ", eventId " + eventId);
        return null;
    }
}
