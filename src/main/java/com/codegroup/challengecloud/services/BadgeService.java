package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.BadgeDao;
import com.codegroup.challengecloud.model.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nipel-Crumple on 10.05.2015.
 */
@Service("badgeService")
public class BadgeService {

    @Autowired
    BadgeDao badgeDao;

    @Transactional
    public Badge findById(String badgeId) {
        return badgeDao.findById(badgeId);
    }

    @Transactional
    public Badge findByName(String badgeName) {
        return badgeDao.findByName(badgeName);
    }

    @Transactional
    public List<Badge> findByEventId(String eventId) {
        return badgeDao.findByEventId(eventId);
    }

    @Transactional
    public void update(Badge badge) {
        badgeDao.update(badge);
    }
}
