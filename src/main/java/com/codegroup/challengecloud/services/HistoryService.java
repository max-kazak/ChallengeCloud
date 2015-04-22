package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.HistoryDao;
import com.codegroup.challengecloud.model.History;
import com.codegroup.challengecloud.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Krokhin on 21.04.2015.
 */
@Service("historyService")
public class HistoryService {

    @Autowired
    HistoryDao historyDao;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    public void setHistoryDao(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    @Transactional
    public void updateHistory(History history) {
        historyDao.update(history);
    }

    @Transactional
    public History createHistory(String userId, Timestamp timestamp, String eventId, String refId) {
        History history = new History();
        history.setId(Generator.generateId());
        history.setUser(userService.findById(userId));
        history.setTimestamp(timestamp);
        history.setEvent(eventService.findById(eventId));
        history.setRefId(refId);

        return history;
    }

    @Transactional
    public void deleteHistory(History history) {
        historyDao.delete(history);
    }

    @Transactional
    public History findByRefId(String refId) {
        return historyDao.findByRefId(refId);
    }

    @Transactional
    public History findById(String historyId) {
        return historyDao.findById(historyId);
    }

    @Transactional
    public Set<History> findHistoryForCurrentUser() {
        return historyDao.getHistoryForUser(userService.getCurrentUser());
    }
}
