package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.services.PostService;

//import javafx.geometry.Pos;
import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 15.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PostTest {
    private static final String FIRST_URL = "https://twitter.com/Yefim_ka/status/577231985401290754";
    private static final String SECOND_URL = "https://twitter.com/Yefim_ka/status/577232167299858433";
    private static final String POST_ID = "1";
    private static final String SUBSCRIPTION_ID = "11";

    @Autowired
    public SessionFactory sessionFactory;
    @Resource
    private PostService postService;

    @Test
    public void testFindById() {
        Assert.assertNotNull(postService.findById(POST_ID));
        /*change by Yefim*/
        Assert.assertEquals(postService.findById(POST_ID).getUrl(), FIRST_URL);
    }

    /**
     * @author Yefim
     */
    @Test
    public void testFindBySubscriptionId() {
        List<Post> postList = postService.findBySubscriptionId(SUBSCRIPTION_ID);
        Assert.assertNotNull(postList);
        Assert.assertEquals(postList.get(0).getUrl(), FIRST_URL);
        Assert.assertEquals(postList.get(1).getUrl(), SECOND_URL);
    }
}
