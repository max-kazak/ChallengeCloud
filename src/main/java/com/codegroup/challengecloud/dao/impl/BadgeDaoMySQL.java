package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.BadgeDao;
import com.codegroup.challengecloud.model.Badge;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
@Repository("badgeDao")
public class BadgeDaoMySQL extends HibernateDao implements BadgeDao {

    private static final Logger logger = Logger.getLogger(BadgeDaoMySQL.class);

    @Override
    @Transactional
    public Badge findById(String badgeId) {
        logger.info("getting badge by id = " + badgeId);
        List badges = find("from Badge where badge_id = ?", badgeId);
        return (Badge) badges.get(0);
    }


    @Override
    @Transactional
    public Badge findByName(String badgeName) {
        logger.info("getting badge by name = " + badgeName);
        List badges = find("from Badge where name = ?", badgeName);
        return (Badge) badges.get(0);
    }

    @Override
    @Transactional
    public Badge findByEventId(String eventId) {
        logger.info("getting badge by eventId = " + eventId);
        List badges = find("from Badge where event_id = ?", eventId);
        return (Badge) badges.get(0);
    }

    @Override
    @Transactional
    public void update(Badge badge) {
        logger.info("updating badge with id = " + badge.getId());
        update(badge);
    }
}
