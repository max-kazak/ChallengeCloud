package com.codegroup.challengecloud.services.events;

import com.codegroup.challengecloud.model.User;
import org.springframework.context.ApplicationContext;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
public class TwitterPostEvent extends CCloudEvent {


    private User user;
    private long timePost;
    private String postId;
    private String id;


    /**
     * @param o   - object from that event was published
     * @param msg - message of the applied event
     * @param timePost - time when post was done
     * @param postId - ID of post was done
     * @param user - user who has done the post
     * @author Nipel-Crumple
     */

    public TwitterPostEvent(Object o, String msg, long timePost, String postId, User user) {
        super(o, msg);
        this.msg = msg;
        this.timePost = timePost;
        this.postId = postId;
        this.user = user;
    }

    public long getTimePost() {
        return timePost;
    }

    public String getPostId() {
        return postId;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "TwitterPost happened:" + msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
