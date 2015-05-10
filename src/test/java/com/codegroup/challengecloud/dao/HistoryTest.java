package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.*;
import com.codegroup.challengecloud.services.ChallengeService;
import com.codegroup.challengecloud.services.HistoryService;
import com.codegroup.challengecloud.services.SubscriptionService;
import com.codegroup.challengecloud.services.UserService;
import junit.framework.Assert;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Krokhin on 21.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HistoryTest {
    private static final String EXCPECTED_EVENT = "1";
    private static final String FIRST_EXCPECTED_ID = "4f140f81bbad8692";
    private static final String SECOND_EXCPECTED_ID = "84d4e0fd55e6315e";
    private static final String EXCPECTED_REF_ID = "577231985401290754";
    private static final String USER_ID = "d8fbf948694caf35";

    @Autowired
    public SessionFactory sessionFactory;

    @Autowired
    public BadgeDao badgeDao;

    @Autowired
    public HistoryDao historyDao;

    @Resource
    private HistoryService historyService;

    @Resource
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ChallengeService challengeService;

    @Test
    public void testFindByRefId() {
        Assert.assertEquals(EXCPECTED_REF_ID, historyService.findByRefId(EXCPECTED_REF_ID).getRefId());
    }

    @Test
    public void testFindById() {
        Assert.assertNotNull(historyService.findById(SECOND_EXCPECTED_ID));
        Assert.assertEquals(SECOND_EXCPECTED_ID, historyService.findById(SECOND_EXCPECTED_ID).getId());
    }

    @Test
    public void testFindHistoryForUser() {
        User user = userService.findById(USER_ID);
        Set<History> allNotes = historyDao.getHistoryForUser(user);
        Assert.assertNotNull(allNotes);
        Iterator<History> iterator = allNotes.iterator();
        int amount = 0;
        while (iterator.hasNext()) {
            if (amount < 2) {
                History tempHistory = iterator.next();
                if (amount == 0) {

                    Assert.assertEquals(tempHistory.getId(), FIRST_EXCPECTED_ID);
                } else {
                    Assert.assertEquals(tempHistory.getId(), SECOND_EXCPECTED_ID);
                }
                amount++;
            } else break;
        }
    }

    @Test
    public void getTweetsByChallengeAndUser() {
        User user = userService.findByLogin("Yefim");
        Assert.assertNotNull(user);
        Challenge challenge = challengeService.findById("876aee6e27926e59");
        Assert.assertNotNull(challenge);
        Subscription subscription = subscriptionService.findById("13");
        long num = historyService.getNumberOfTweetsForUserBySubscription(user, subscription);
        Assert.assertEquals(2, num);
    }

    @Ignore("specific test for every user but it works")
    @Test
    public void testCounter() {
        User user = userService.findByLogin("Nipel-Crumple");
        long actual = historyService.getNumberOfUserTweets(user);
        Assert.assertEquals((long) 6, actual);
    }

    @Ignore("Test is very specific for every developer")
    @Test
    public void getUserBadgesTest() {
        User user = userService.findByEmail("volterr@gmail.com");
        Set<Badge> badges = historyService.getUserBadges(user);
        Badge badge = badgeDao.findByName("Mister Twitter");

        Assert.assertNotNull(badges);
        Assert.assertEquals(badge.getName(), "Mister Twitter");
        Assert.assertFalse(badges.isEmpty());
        Assert.assertTrue(badges.contains(badge));
    }
}
