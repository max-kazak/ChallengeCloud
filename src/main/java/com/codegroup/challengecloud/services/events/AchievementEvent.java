package com.codegroup.challengecloud.services.events;

import com.codegroup.challengecloud.constants.EventIds;
import com.codegroup.challengecloud.model.Badge;
import com.codegroup.challengecloud.model.Event;
import com.codegroup.challengecloud.model.User;

/**
 * Created by Nipel-Crumple on 02.05.2015.
 */
public class AchievementEvent extends CCloudEvent {

    private User user;
    private long time;
    private String id;
    private Badge badge;
    /**
     * @param o   - object from that event was published
     * @param msg - message of the applied event
     * @param time - when user gets achievement
     * @param user - who gave achievement
     * @param badge - type of achievement
     * @author Nipel-Crumple
     */
    public AchievementEvent(Object o, String msg, long time, User user, Badge badge) {
        super(o, msg);
        this.id = EventIds.ACHIEVMENTEVENT_ID;
        this.time = time;
        this.user = user;
        this.badge = badge;
    }

    @Override
    public String toString() {
        return "AchievementEvent happenned" + msg;
    }

    public User getUser() {
        return user;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String getId() {
        return id;
    }

    public Badge getBadge() {
        return badge;
    }
}
