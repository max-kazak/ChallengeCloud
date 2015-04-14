package com.codegroup.challengecloud.config;

import com.codegroup.challengecloud.services.scheduler.ScheduleWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Nipel-Crumple on 08.04.2015.
 */
@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Bean
    public ScheduleWorker scheduleTaskService() {
        return new ScheduleWorker();
    }
}
