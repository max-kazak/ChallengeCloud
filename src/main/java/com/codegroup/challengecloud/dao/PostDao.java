package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Post;

import java.util.List;

/**
 * Created on 24.02.2015
 * @author Andrey
 */
public interface PostDao {

    public void save(Post post);
    public void update(Post post);
    public void delete(Post post);

    public Post findById(String id);
    public List<Post> findBySubscriptionId(String subscriptionId);
}
