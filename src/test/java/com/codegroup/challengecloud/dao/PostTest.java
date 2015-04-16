package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.services.PostService;

import com.codegroup.challengecloud.services.TwitterDownloadService;
import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.social.twitter.api.SearchResults;

import java.util.List;

/**
 * Created by Andrey on 15.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PostTest {
    private static final String POST_ID_FIRST = "577231985401290754";
    private static final String POST_ID_SECOND = "577232167299858433";

    @Autowired
    public SessionFactory sessionFactory;
    @Resource
    private PostService postService;
    @Resource
    private TwitterDownloadService twitterDownloadService;

    @Test
    public void testFindById() {
        Assert.assertNotNull(postService.findById(POST_ID_FIRST));
        /*change by Yefim*/
        Assert.assertEquals(postService.findById(POST_ID_FIRST).getId(), POST_ID_FIRST);
    }
}
