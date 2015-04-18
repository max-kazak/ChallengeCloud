package com.codegroup.challengecloud.services.events;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
public class TwitterPostEvent extends CCloudEvent {
    /**
     * @param o   - object from that event was published
     * @param msg - message of the applied event
     * @author Nipel-Crumple
     */
    public TwitterPostEvent(Object o, String msg) {
        super(o, msg);
    }

    @Override
    public String toString() {
        return "TwitterPost happened:" + msg;
    }
}
