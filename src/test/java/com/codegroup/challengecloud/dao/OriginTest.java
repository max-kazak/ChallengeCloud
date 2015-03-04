package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.services.OriginService;
import junit.framework.Assert;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Yefim on 04.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class OriginTest {

    private static final String ORIGIN_ID = "twit_id";
    private static final String ORIGIN_NAME = "Twitter";

    @Autowired
    public SessionFactory sessionFactory;
    @Resource
    private OriginService originService;

    @Test
    public void testFindById() {
        /*Origin result = originService.findById(ORIGIN_ID);*/
        Assert.assertNotNull(originService.findById(ORIGIN_ID));
        /*Assert.assertEquals(result.getId(), ORIGIN_ID);
        Assert.assertEquals(result.getName(), ORIGIN_NAME);*/
    }
}
