package com.codegroup.challengecloud.services.events;

import com.codegroup.challengecloud.constants.EventIds;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.User;

/**
 * Created by Nipel-Crumple on 07.05.2015.
 */
public class ChallengeCompletedEvent extends CCloudEvent {

    private Challenge challenge;
    private static final String id = EventIds.CHALLENGECOMPLETEDEVENT_ID;

    /**
     * @param o   - object from that event was published
     * @param msg - message of the applied event
     * @author Nipel-Crumple
     */
    public ChallengeCompletedEvent(Object o, String msg, User user, long time, Challenge challenge) {
        super(o, msg, user, time);
        this.challenge = challenge;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "ChallengeCompletedEvent happenned" + msg;
    }
}
