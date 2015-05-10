package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Badge;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
public interface BadgeDao {
    public Badge findById(String badgeId);
    public Badge findByName(String badgeName);
    public List<Badge> findByEventId(String eventId);
    public void update(Badge badge);
}
