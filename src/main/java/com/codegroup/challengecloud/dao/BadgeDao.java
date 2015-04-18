package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Badge;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
public interface BadgeDao {
    @Transactional
    public Badge findById(String badgeId);
    @Transactional
    public void update(Badge badge);
}
