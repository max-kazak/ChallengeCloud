package com.codegroup.challengecloud.dao.impl;

import java.util.List;

import com.codegroup.challengecloud.dao.PostDao;
import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created on 24.02.2015.
 *
 * @author Andrey
 */
@Repository("postDao")
public class PostDaoMySQL extends HibernateDao implements PostDao {

    private static final Logger log = Logger.getLogger(UserDaoMySQL.class);

    @Override
    public void save(Post post) {
        log.debug("Saving post " + post.getId());
        getSession().save(post);
    }

    @Override
    public void update(Post post) {
        log.debug("Updating post " + post.getId());
        getSession().update(post);
    }

    @Override
    public void delete(Post post) {
        log.debug("Deleting post " + post.getId());
        getSession().delete(post);
    }

    @Override
    public Post findById(String id) {
        log.debug("Looking for post by id = " + id);
        List list = find("from Post where post_id = ?", id);
        return (Post) list.get(0);
    }

    /**
     * @author Yefim
     */
    @Override
    public List<Post> findBySubscriptionId(String subscriptionId) {
        log.debug("Find all posts with subscription id " + subscriptionId);
        List<Post> list = (List<Post>) (List<?>) find("from Post where subscription_id = ?", subscriptionId);
        return list;
    }
}
