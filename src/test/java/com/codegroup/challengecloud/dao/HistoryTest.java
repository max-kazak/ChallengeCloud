package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.services.HistoryService;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Krokhin on 21.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HistoryTest {
    @Autowired
    public SessionFactory sessionFactory;

    @Resource
    private HistoryService historyService;
}
