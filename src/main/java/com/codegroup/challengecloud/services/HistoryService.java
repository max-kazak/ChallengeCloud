package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.HistoryDao;
import com.codegroup.challengecloud.model.Badge;
import com.codegroup.challengecloud.model.History;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.utils.Generator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by Krokhin on 21.04.2015.
 */
@Service("historyService")
public class HistoryService {
    private static Logger log = Logger.getLogger(HistoryService.class);
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
        log.debug("Creating history note with id = " + history.getId());
        historyDao.save(history);
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

    @Transactional
    public long getNumberOfUserTweets(User user) {
        return historyDao.getNumberOfTwitterPosts(user);
    }

    @Transactional
    public Set<Badge> getUserBadges(User user){
        return historyDao.getUserBadges(user);
    }
}
