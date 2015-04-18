package com.codegroup.challengecloud.services.events;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
public class CCloudEvent extends ApplicationEvent {

    public String msg;

    /**
     * @param o - object from that event was published
     * @param msg - message of the applied event
     * @author Nipel-Crumple
     * */
    public CCloudEvent(Object o, String msg) {
        super(o);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CCloudEvent happened: " + msg;
    }
}
