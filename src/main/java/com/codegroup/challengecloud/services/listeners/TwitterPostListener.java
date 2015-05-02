package com.codegroup.challengecloud.services.listeners;

import com.codegroup.challengecloud.dao.BadgeDao;
import com.codegroup.challengecloud.model.Badge;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.HistoryService;
import com.codegroup.challengecloud.services.events.AchievementEvent;
import com.codegroup.challengecloud.services.events.TwitterPostEvent;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

import java.sql.Timestamp;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by Nipel-Crumple on 02.05.2015.
 */
@Service("twitterPostListener")
public class TwitterPostListener implements ApplicationListener<TwitterPostEvent> {

    private static final Logger logger = Logger.getLogger(TwitterPostEvent.class);
    @Autowired
    HistoryService historyService;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    BadgeDao badgeDao;

    @Override
    public void onApplicationEvent(TwitterPostEvent event) {
        long numberTwits = historyService.getNumberOfUserTweets(event.getUser());
        Badge badge = badgeDao.findByEventId(event.getId());

        long condition = 0;
        JSONObject jsonCondition = null;
        try {
            jsonCondition = new JSONObject(badge.getCondition());
            if (jsonCondition != null) {
                condition = jsonCondition.getLong("twitterPosts");
                logger.info("Condition in Mr Twitter " + condition);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        User user = event.getUser();
        Set<Badge> userBadges = historyService.getUserBadges(user);

        if (numberTwits >= condition && !userBadges.contains(badge)) {
            logger.info("condition: " + condition + " " + userBadges.contains(badge));
            long time = event.getTimePost();
            AchievementEvent achievementEvent = new AchievementEvent(applicationContext, "got Achievement",
                    time, user, badge);

            applicationContext.publishEvent(achievementEvent);
        }
    }
}
