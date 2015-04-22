package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.HistoryDao;
import com.codegroup.challengecloud.model.History;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

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
        log.debug("find history record with reID " + refId);
        List list = find("from History where REF_ID = ?", refId);
        return (History) list.get(0);
    }

    @Override
    public History findByHistoryId(String historyId) {
        log.debug("find history record by  PK:  " + historyId);
        List list = find("from History where HISTORY_ID = ?", historyId);
        return (History) list.get(0);
    }
}
