package com.codegroup.challengecloud.services.listeners;

import com.codegroup.challengecloud.constants.EventIds;
import com.codegroup.challengecloud.services.HistoryService;
import com.codegroup.challengecloud.services.UserService;
import com.codegroup.challengecloud.services.events.CCloudEvent;
import com.codegroup.challengecloud.services.events.TwitterPostEvent;
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
@Service("HistoryListener")
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
            event.setId(EventIds.TWITTERPOSTEVENT_ID);
            String userId = ((TwitterPostEvent) event).getUser().getId();
            String postId = ((TwitterPostEvent) event).getPostId();
            Timestamp timestamp = new Timestamp(((TwitterPostEvent) event).getTimePost());
            log.debug("Creating history note with TwitterPostEvent " + ((TwitterPostEvent) event).getPostId());
            historyService.createHistory(userId, timestamp,((TwitterPostEvent) event).getId(), postId);
        }
    }
}

