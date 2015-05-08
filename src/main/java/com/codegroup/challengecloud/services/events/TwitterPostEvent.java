package com.codegroup.challengecloud.services.events;

import com.codegroup.challengecloud.constants.EventIds;
import com.codegroup.challengecloud.model.User;
import org.springframework.context.ApplicationContext;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
public class TwitterPostEvent extends CCloudEvent {

    private String postId;
    private static final String id = EventIds.TWITTERPOSTEVENT_ID;

    /**
     * @param o        - object from that event was published
     * @param msg      - message of the applied event
     * @param timePost - time when post was done
     * @param postId   - ID of post was done
     * @param user     - user who has done the post
     * @author Nipel-Crumple
     */

    public TwitterPostEvent(Object o, String msg, User user, long timePost, String postId) {
        super(o, msg, user, timePost);
        this.postId = postId;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public User getUser() {
        return user;
    }

    public String getPostId() {
        return postId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TwitterPostEvent happened:" + msg;
    }
}
