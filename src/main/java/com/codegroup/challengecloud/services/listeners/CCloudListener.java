package com.codegroup.challengecloud.services.listeners;

import com.codegroup.challengecloud.services.HistoryService;
import com.codegroup.challengecloud.services.UserService;
import com.codegroup.challengecloud.services.events.CCloudEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Service;

/**
 * <p>Class that extends the {@link org.springframework.context.ApplicationListener}.
 * It allows to listen to
 * CCloudEvents in CCLoud application </p>
 * Created by Nipel-Crumple on 18.04.2015.
 */
@Service("cCloudEventsListener")
public class CCloudListener implements ApplicationListener<CCloudEvent> {

    private static final Logger log = Logger.getLogger(CCloudListener.class);

    @Autowired
    HistoryService historyService;

    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    UserService userService;

    @Override
    public void onApplicationEvent(CCloudEvent event) {
        log.info(event.toString());
    }
}

