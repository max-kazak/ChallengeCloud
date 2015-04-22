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
    private static final String EXCPECTED_EVENT = "1";
    private static final String EXCPECTED_ID = "123";
    private static final String EXCPECTED_REF_ID = "577231985401290754";

    @Autowired
    public SessionFactory sessionFactory;

    @Resource
    private HistoryService historyService;


    @Test
    public void testFindByRefId() {
        Assert.assertEquals(EXCPECTED_REF_ID, historyService.findByRefId(EXCPECTED_REF_ID).getRefId());
    }
    @Test
    public void testFindById() {
        Assert.assertNotNull(historyService.findById(EXCPECTED_ID));
        Assert.assertEquals(EXCPECTED_ID, historyService.findById(EXCPECTED_ID).getId());
    }
}
