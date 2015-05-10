package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Badge;
import com.codegroup.challengecloud.services.BadgeService;
import com.codegroup.challengecloud.services.EventService;
import com.codegroup.challengecloud.services.ImageService;
import junit.framework.Assert;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BadgeTest {

    private final String id ="aislfu4odefsrgts";
    private final String name ="Mister Twitter";
    private final String description = "Congratulation! You've created 5 posts and got 'Mister Twitter' badge!";
    private final String imageId ="dadsf87a823e98d2";
    private final String eventId ="2";
    private final String condition = "{ \"twitterPosts\":5 }";

    private Badge expectedBadge;
    @Autowired
    BadgeService badgeService;

    @Autowired
    ImageService imageService;

    @Autowired
    EventService eventService;

    @Before
    public void getExpectedBadge() {
        Badge badge = new Badge();
        badge.setId(id);
        badge.setName(name);
        badge.setDescription(description);
        badge.setImage(imageService.getImage(imageId));
        badge.setEvent(eventService.findById(eventId));
        badge.setCondition(condition);
        this.expectedBadge = badge;
    }
    @Test
    public void findByIdTest() {
        Badge actualBadge = badgeService.findById("aislfu4odefsrgts");
        Assert.assertNotNull(actualBadge);
        Assert.assertEquals(id, actualBadge.getId());
        Assert.assertEquals(expectedBadge.getEvent().getClazz(),actualBadge.getEvent().getClazz());
    }

    @Test
    public void findByNameTest() {
        Badge actual = badgeService.findByName(name);
        Assert.assertNotNull(actual);
        Assert.assertEquals(description, actual.getDescription());
    }

    @Test
    public void findByEventId() {
        List<Badge> actual = badgeService.findByEventId(eventId);
        Assert.assertNotNull(actual);
    }

    @Test
    public void conditionJsonTest() {
        List<Badge> actual = badgeService.findByEventId("4");
        Assert.assertNotNull(actual);
        String challengeId = null;
        long posts = 0;
        for (Badge badge : actual) {
            try {
                JSONObject json = new JSONObject(badge.getCondition());
                if (json.getString("challenge_id").equals("7362914e9fade238")) {
                    challengeId = json.getString("challenge_id");
                    posts = json.getLong("posts");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Assert.assertNotNull(challengeId);
        Assert.assertEquals("7362914e9fade238", challengeId);
        Assert.assertEquals(3, posts);
    }
}
