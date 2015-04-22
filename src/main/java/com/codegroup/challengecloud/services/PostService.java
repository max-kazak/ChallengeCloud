package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.PostDao;
import com.codegroup.challengecloud.model.Origin;
import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.services.events.TwitterPostEvent;
import com.codegroup.challengecloud.utils.Generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * Created by Andrey on 15.03.2015.
 */
@Service("postService")
public class PostService {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    PostDao postDao;
    @Autowired
    private UserService userService;
    @Autowired
    private SubscriptionService subscriptionService;

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Transactional
    public Post createPost(Subscription subscription, Date date, Origin origin) {
        Post post = new Post();

        post.setId(Generator.generateId());
        post.setSubscription(subscription);
        post.setDate(date);
        post.setOrigin(origin);

        postDao.save(post);

        return post;
    }

    /**
     * created on 07.04.2015 by Vladimir Zhdanov
     * @param postId id for new post
     * @param subscription
     * @param date
     * @param origin
     * @return
     */
    @Transactional
    public Post createPost(String postId, Subscription subscription, Date date, Origin origin) {
        Post post = new Post();

        post.setId(postId);
        post.setSubscription(subscription);
        post.setDate(date);
        post.setOrigin(origin);

        postDao.save(post);

        return post;
    }

    @Transactional
    public Post findById(String postId) {
        return postDao.findById(postId);
    }

    @Transactional
    public List<Post> findPostsByUserSubscriptions() {
        return postDao.findPostsByUserSubscriptions(getUserSubscriptions());
    }

    private List<Subscription> getUserSubscriptions() {
        return subscriptionService.findByUserId(userService.getCurrentUser().getId());
    }
}
