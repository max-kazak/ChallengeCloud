package com.codegroup.challengecloud.config;

import com.codegroup.challengecloud.services.TwitterDownloadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Inject;

/**
 * Created by Nipel-Crumple on 08.04.2015.
 */
@Configuration
@EnableScheduling
public class ScheduleConfig {
    private static Logger log = Logger.getLogger(ScheduleConfig.class);

    @Autowired
    TwitterDownloadService twitterDownloadService;

    /**
     * Every 2 minutes adding twitterPosts
     * @author Nipel-Crumple
     */
    @Scheduled(cron="0 0/2 * * * ?")
    public void scheduleTwitterDownloadPosts() {
        log.debug("Launching download posts task");
        twitterDownloadService.addAllUsersTwitterPosts();
    }
}
