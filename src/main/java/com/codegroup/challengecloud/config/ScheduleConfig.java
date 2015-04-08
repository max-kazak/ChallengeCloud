package com.codegroup.challengecloud.config;

import com.codegroup.challengecloud.services.ScheduleTaskService;
import com.codegroup.challengecloud.services.TwitterDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.social.twitter.api.Twitter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Nipel-Crumple on 08.04.2015.
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }

    /**
     * Here should be injected @Bean (I guess it is TwitterDownloadService) whose methods will be invoked
     * @author Nipel-Crumple
     */

    @Bean
    public ScheduleTaskService getScheduleTaskService() {
        return new ScheduleTaskService();
    }
}
