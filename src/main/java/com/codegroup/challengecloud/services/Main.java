package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.config.ScheduleConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by Nipel-Crumple on 08.04.2015.
 */
public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ScheduleConfig.class);
    }
}
