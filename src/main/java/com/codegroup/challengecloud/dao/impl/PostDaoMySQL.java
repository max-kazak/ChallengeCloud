package com.codegroup.challengecloud.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.codegroup.challengecloud.dao.PostDao;
import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.model.User;

import com.codegroup.challengecloud.services.SubscriptionService;
import com.codegroup.challengecloud.services.UserService;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created on 24.02.2015.
 *
 * @author Andrey
 */
@Repository("postDao")
public class PostDaoMySQL extends HibernateDao implements PostDao {

    private static final Logger log = Logger.getLogger(PostDaoMySQL.class);

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
        return (Post) ((list.size()==0) ? null : list.get(0));// Modified on 07.04.2015 by Vladimir Zhdanov
    }

    /**
     * @author Yefim
     */
    @Override
    public List<Post> findPostsByUserSubscriptions(List<Subscription> userSubscriptions) {
        log.debug("Find all posts fo user");
        List<String> parameters = new ArrayList<>();
        log.debug("Find subs? " + !userSubscriptions.isEmpty());
        if (!userSubscriptions.isEmpty()) {
            for (Subscription tempSubscription : userSubscriptions) {
                parameters.add(tempSubscription.getId());
            }

            return (List<Post>) (List<?>) find("from Post where subscription_id in (:param)", parameters);
        }
        return null;
    }
}
