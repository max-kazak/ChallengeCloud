package com.challengecorp.sc;

import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by Max on 24.01.2015.
 */
public class HibernateTest {

    @Test
    public void findUser(){
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/contextConfig.xml");

        UserService userService = (UserService) appContext.getBean("userService");

        User testUser = userService.findByEmail("volterr@gmail.com");

        assertEquals("d6fbf948694caf35", testUser.getId());
    }

}
