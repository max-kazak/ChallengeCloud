package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.services.events.CCloudEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EventListenerTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void listenerToCCloudEventsTest() {
        CCloudEvent event = new CCloudEvent(applicationContext, "Here we are");
        applicationContext.publishEvent(event);
    }
}
