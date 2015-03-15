package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.PostDao;
import com.codegroup.challengecloud.model.Origin;
import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.utils.Generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

/**
 * Created by Andrey on 15.03.2015.
 */
@Service("postService")
public class PostService {
	@Autowired
    PostDao postDao;

    public void setSubscriptionDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Transactional
    public Post createPost(Subscription subscription, Date date, String url, Origin origin) {
        Post post = new Post();

        post.setId(Generator.generateId());
        post.setSubscription(subscription);
        post.setDate(date);
        post.setUrl(url);
        post.setOrigin(origin);
        
        postDao.save(post);

        return post;
    }

    @Transactional
    public Post findById(String postId) {
        return postDao.findById(postId);
    }
}
