package com.codegroup.challengecloud.services.listeners;

import com.codegroup.challengecloud.dao.BadgeDao;
import com.codegroup.challengecloud.model.Badge;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.BadgeService;
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
    BadgeService badgeService;

    @Override
    public void onApplicationEvent(TwitterPostEvent event) {
        long numberTwits = historyService.getNumberOfUserTweets(event.getUser());

        //TODO : this case works only if TwitterPostEvent is in DB in amount of 1.
        Badge badge = badgeService.findByEventId(event.getId()).get(0);

        long condition = 0;
        try {
            JSONObject jsonCondition = new JSONObject(badge.getCondition());
            condition = jsonCondition.getLong("twitterPosts");
            logger.info("Condition in Mr Twitter " + condition);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        User user = event.getUser();
        Set<Badge> userBadges = historyService.getUserBadges(user);

        if (numberTwits >= condition && !userBadges.contains(badge)) {
            logger.info("condition: " + condition + " " + userBadges.contains(badge));
            long time = event.getTime();
            AchievementEvent achievementEvent = new AchievementEvent(applicationContext, "got Achievement",
                    user, time, badge);

            applicationContext.publishEvent(achievementEvent);
            logger.info(achievementEvent.toString());
        }
    }
}
