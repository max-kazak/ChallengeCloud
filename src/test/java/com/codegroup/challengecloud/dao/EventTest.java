package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.services.EventService;
import junit.framework.Assert;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Krokhin on 15.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EventTest {
    @Autowired
    public SessionFactory sessionFactory;

    @Resource
    private EventService eventService;

    @Test
    public void testFindById() {
        Assert.assertNotNull(eventService.findById("1"));
        Assert.assertEquals("1", eventService.findById("1").getId());
    }
}
