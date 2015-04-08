package com.codegroup.challengecloud.services;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Nipel-Crumple on 08.04.2015.
 */
public class ScheduleTaskService {

    @Scheduled(fixedDelay = 1000)
    public void printItTask() {
        System.out.println("Print it every second");
    }
}
