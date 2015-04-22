package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.services.HistoryService;
import junit.framework.Assert;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Krokhin on 21.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HistoryTest {
    private static final String EXCPECTED_USER = "d8fbf948694caf35";
    private static final String EXCPECTED_TIME = "2008-01-01 00:00:01";
    private static final String EXCPECTED_EVENT = "1";
    private static final String EXCPECTED_REF = "577231985401290754";

    @Autowired
    public SessionFactory sessionFactory;

    @Resource
    private HistoryService historyService;

    @Test
    public void testFindByRef() {
        Assert.assertNotNull(historyService.findByHistoryId("123"));
    }
}
