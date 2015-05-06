package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.dao.impl.ChallengeDaoMySQL;
import com.codegroup.challengecloud.services.ChallengeService;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Vladimir Zhdanov on 28.02.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ChallengeTest{
	
	@Autowired
    public SessionFactory sessionFactory;
    @Resource
    private ChallengeService challengeService;

    @Test
    public void testFindById() {
        Assert.assertNotNull(challengeService.findById("876aee6e27926e59"));
        System.out.println(challengeService.findById("876aee6e27926e59").getTitle());
    }
    
    @Test
    public void testFindByTitle() {
        Assert.assertNotNull(challengeService.findByTitle("Run about 2 km a day"));
        System.out.println(challengeService.findByTitle("Run about 2 km a day").getTitle());
    }

    @Test
    public void testFindAll() {
        Assert.assertTrue(challengeService.findAll().size() > 0);
        System.out.println("There are " + challengeService.findAll().size() + " Challenges");
    }
}


