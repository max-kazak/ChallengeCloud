package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.services.UserService;
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
public class UserTest {

    private static final String LOGIN = "volterr";
    private static final String ID = "d6fbf948694caf35";
    private static final String EMAIL = "volterr@gmail.com";
    private static final String PASS = "$2a$10$rdHCgQDkMqQSk9IK8rhsVu1XhC/Y/VA5ohsMgGtjasABH7b.WAWzi";
    private static final String NAME = "max kazakov";
    @Autowired
    public SessionFactory sessionFactory;
    @Resource
    private UserService userService;

    @Test
    public void testFindById() {
        Assert.assertNotNull(userService.findById(ID));
        Assert.assertEquals(userService.findById(ID).getId(), ID);
    }

    @Test
    public void testFindByLogin() {
        Assert.assertNotNull(userService.findByLogin(LOGIN));
        Assert.assertEquals(userService.findByLogin(LOGIN).getLogin(), LOGIN);
    }

    @Test
    public void testFindByEmail() {
        Assert.assertNotNull(userService.findByEmail(EMAIL));
        Assert.assertEquals(userService.findByEmail(EMAIL).getEmail(), EMAIL);
    }
}
