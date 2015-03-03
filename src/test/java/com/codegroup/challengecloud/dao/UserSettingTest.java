package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.services.UserSettingService;
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
public class UserSettingTest {

    private static final String USER_ID = "d6fbf948694caf35";
    private  static final String USER_LANG = "English";

    @Autowired
    public SessionFactory sessionFactory;
    @Resource
    private UserSettingService userSettingService;

    @Test
    public void testFindById() {
        Assert.assertNotNull(userSettingService.findById(USER_ID));
        Assert.assertEquals(userSettingService.findById(USER_ID).getLang(), USER_LANG);
    }
}
