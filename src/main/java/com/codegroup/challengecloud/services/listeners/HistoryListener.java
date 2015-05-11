package com.codegroup.challengecloud.services.listeners;

import com.codegroup.challengecloud.constants.EventIds;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.services.HistoryService;
import com.codegroup.challengecloud.services.UserService;
import com.codegroup.challengecloud.services.events.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * <p>Class that extends the {@link org.springframework.context.ApplicationListener}.
 * It allows to listen to
 * CCloudEvents in CCLoud application </p>
 * Created by Nipel-Crumple on 18.04.2015.
 */
@Service("historyListener")
public class HistoryListener implements ApplicationListener<CCloudEvent> {

    private static final Logger log = Logger.getLogger(HistoryListener.class);

    @Autowired
    HistoryService historyService;

    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    UserService userService;

    @Override
    public void onApplicationEvent(CCloudEvent event) {
        log.info(event.toString());
        if (event instanceof TwitterPostEvent) {
            String postId = ((TwitterPostEvent) event).getPostId();
            log.debug("Creating history note with TwitterPostEvent " + event.getId());
            historyService.createHistory(event.getUser(), new Timestamp(event.getTime()), event.getId(), postId);
        }

        if (event instanceof AchievementEvent) {
            log.debug("Creating history note with AchievementEvent " + event.getId());
            historyService.createHistory(event.getUser(),
                    new Timestamp(event.getTime()), event.getId(), ((AchievementEvent) event).getBadge().getId());
        }

        if (event instanceof ChallengeCompletedEvent) {
            log.debug("Creating history note with ChallengeCompletedEvent " + event.getId());
            historyService.createHistory(event.getUser(),
                    new Timestamp(event.getTime()),
                    EventIds.CHALLENGECOMPLETEDEVENT_ID,
                    ((ChallengeCompletedEvent) event).getChallenge().getId());
        }

        if (event instanceof SubscriptionEvent) {
            log.debug("Creating history note with SubscriptionEvent " + event.getId());
            historyService.createHistory(event.getUser(),
                    new Timestamp(event.getTime()),
                    event.getId(),
                    ((SubscriptionEvent) event).getSubscription().getId());
        }
    }
}

