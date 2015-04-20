package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Krokhin on 19.04.2015.
 */

@Embeddable
class HistoryPK {

    @Column(name = "USER_ID", unique = false, nullable = false)
    private String userID;

    @Column(name = "TIME", unique = false, nullable = false)
    private Timestamp timestamp;

    @Column(name = "EVENT_ID", unique = false, nullable = false)
    private String eventId;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

@Entity
@Table(name = "history", catalog = "challenger"/*, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USER_ID", "TIME", "EVENT_ID"})
}*/)
public class History implements Serializable {

    private static final long serialVersionUID = 1L;

    String userId;
    Timestamp timestamp;
    String eventId;
    String refId;

    @EmbeddedId
    private HistoryPK historyPK;

    public String getUserId() {
        return historyPK.getUserID();
    }

    public void setUserId(String userId) {
        historyPK.setUserID(userId);
    }

    public Timestamp getTimestamp() {
        return historyPK.getTimestamp();
    }

    public void setTimestamp(Timestamp timestamp) {
        historyPK.setTimestamp(timestamp);
    }

    public String getEventId() {
        return historyPK.getEventId();
    }

    public void setEventId(String eventId) {
        historyPK.setEventId(eventId);
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
        return "History{user_id='" + userId + "\'" +
                ", time='" + timestamp + "\'" +
                ", event_id='" + eventId + "\'" +
                ", ref_id='" + refId + "}";
    }
}
