package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.services.PostService;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Andrey on 15.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PostTest {
	@Autowired
    public SessionFactory sessionFactory;
    @Resource
    private PostService postService;

    @Test
    public void testFindById() {
        Assert.assertNotNull(postService.findById("42"));
        System.out.println(postService.findById("42").getDate());
    }
}
