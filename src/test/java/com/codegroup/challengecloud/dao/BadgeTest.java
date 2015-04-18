package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.dao.impl.BadgeDaoMySQL;
import com.codegroup.challengecloud.model.Badge;
import com.codegroup.challengecloud.services.EventService;
import com.codegroup.challengecloud.services.ImageService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BadgeTest {

    private Badge expectedBadge;
    @Autowired
    BadgeDao badgeDao;

    @Autowired
    ImageService imageService;

    @Autowired
    EventService eventService;

    @Before
    public void getExpectedBadge() {
        Badge badge = new Badge();
        badge.setId("aislfu4odefsrgts");
        badge.setName("Mister Twitter");
        badge.setDescription("Congratulation! You've created 5 posts and got 'Mister Twitter' badge!");
        badge.setImage(imageService.getImage("dadsf87a823e98d2"));
        badge.setEvent(eventService.findById("2"));
        badge.setCondition("{ \"twitterPosts\":5 }");
        this.expectedBadge = badge;
    }
    @Test
    public void findByIdTest() {
        Badge actualBadge = badgeDao.findById("aislfu4odefsrgts");
        Assert.assertNotNull(actualBadge);
        Assert.assertEquals(expectedBadge.getDescription(),actualBadge.getDescription());
    }
}
