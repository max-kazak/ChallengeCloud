package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Krokhin on 19.04.2015.
 */

@Entity
@Table(name = "history", catalog = "challenger", uniqueConstraints = {
        @UniqueConstraint(columnNames = "HISTORY_ID")
})
public class History implements Serializable {

    private static final long serialVersionUID = 1L;

    String refId;
    Event event;
    User user;
    String historyId;
    Timestamp timestamp;

    @Id
    @Column(name = "HISTORY_ID", unique = true, nullable = false)
    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", unique = false, nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Column(name = "TIME", unique = false, nullable = false)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


    @ManyToOne
    @JoinColumn(name = "EVENT_ID", unique = false, nullable = false)
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Column(name = "REF_ID", unique = false, nullable = false)
    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    @Override
    public String toString() {
        return "History{user_id='" + user.getId() + "\'" +
                ", time='" + timestamp + "\'" +
                ", event_id='" + event.getId() + "\'" +
                ", ref_id='" + refId + "}";
    }
}
