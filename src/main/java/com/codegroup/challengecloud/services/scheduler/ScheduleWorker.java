package com.codegroup.challengecloud.services.scheduler;

import com.codegroup.challengecloud.services.TwitterDownloadService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import javax.inject.Inject;

/**
 * Created by Nipel-Crumple on 09.04.2015.
 */
public class ScheduleWorker {

    private static Logger logger = Logger.getLogger(ScheduleWorker.class);
    @Inject
    TwitterDownloadService twitterDownloadService;

    /**
     * Every fifteen seconds adding twitterPosts
     * @author Nipel-Crumple
     */
    @Scheduled(cron="0/15 * * * * ?")
    public void scheduleTwitterDownloadPosts() {
        logger.debug("Launching download posts task");
        twitterDownloadService.addAllUsersTwitterPosts();
    }
}
