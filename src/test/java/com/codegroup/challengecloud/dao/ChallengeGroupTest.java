package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.dao.impl.ChallengeGroupDaoMySQL;
import com.codegroup.challengecloud.services.ChallengeGroupService;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Vladimir Zhdanov on 01.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ChallengeGroupTest{
	
	@Autowired
    public SessionFactory sessionFactory;
    @Resource
    private ChallengeGroupService challengeGroupService;

    @Test
    public void testFindById() {
        Assert.assertNotNull(challengeGroupService.findById("5d2c117e674b39de"));
        System.out.println(challengeGroupService.findById("5d2c117e674b39de").getName());
    }
}


