package com.codegroup.challengecloud.services.events;

import com.codegroup.challengecloud.constants.EventIds;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.model.User;

/**
 * Created by Nipel-Crumple on 11.05.2015.
 */
public class SubscriptionEvent extends CCloudEvent {

    private static final String id = EventIds.SUBSCRIPTIONEVENT_ID;
    private Subscription subscription;
    /**
     * @param o    - object from that event was published
     * @param msg  - message of the applied event
     * @param user
     * @param time @author Nipel-Crumple
     */
    public SubscriptionEvent(Object o, String msg, User user, long time, Subscription subscription) {
        super(o, msg, user, time);
        this.subscription = subscription;
    }

    public Subscription getSubscription() {
        return subscription;
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
        return "SubscriptionEvent happened" + msg;
    }
}
