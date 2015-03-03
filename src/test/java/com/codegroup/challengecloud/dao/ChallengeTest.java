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
}

