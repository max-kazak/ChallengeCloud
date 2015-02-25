package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.dao.impl.SubscriptionDaoMySQL;
import com.codegroup.challengecloud.services.SubscriptionService;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by Nipel-Crumple on 24.02.2015.
 */
public class SubscriptionTest extends BaseTest {

    @Resource
    private SubscriptionService subscriptionService;

    @Test
    public void testFindById() {
        Assert.assertNotNull(subscriptionService.findById("11"));
        System.out.println(subscriptionService.findById("11").getDate());
    }
}
