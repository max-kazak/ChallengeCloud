package com.codegroup.challengecloud.services.events;

import com.codegroup.challengecloud.constants.EventIds;
import com.codegroup.challengecloud.model.User;
import org.springframework.context.ApplicationEvent;

import java.security.Timestamp;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
public class CCloudEvent extends ApplicationEvent {

    protected String msg;
    private static final String id = EventIds.CCLOUDEVENT_ID;
    protected User user;
    protected long time;

    /**
     * @param o   - object from that event was published
     * @param msg - message of the applied event
     * @author Nipel-Crumple
     */
    public CCloudEvent(Object o, String msg, User user, long time) {
        super(o);
        this.msg = msg;
        this.user = user;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return this.user;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "CCloudEvent happened: " + msg;
    }
}
