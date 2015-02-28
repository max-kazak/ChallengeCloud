package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.dao.impl.SubscriptionDaoMySQL;
import com.codegroup.challengecloud.services.SubscriptionService;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Nipel-Crumple on 24.02.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SubscriptionTest{

	@Autowired
    public SessionFactory sessionFactory;
    @Resource
    private SubscriptionService subscriptionService;

    @Test
    public void testFindById() {
        Assert.assertNotNull(subscriptionService.findById("11"));
        System.out.println(subscriptionService.findById("11").getDate());
    }
}
