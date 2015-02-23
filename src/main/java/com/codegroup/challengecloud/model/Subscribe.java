package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Johnny D on 23.02.2015.
 */
@Entity
@Table( name = "subscriptions",
        schema = "challenger",
        uniqueConstraints = {
            @UniqueConstraint( columnNames = {"SUBSCRIPTION_ID", "DATE"})})
public class Subscribe {
    String id;
    String userId;
    String challengeId;
    Date date;

    @Id
    @Column( name = "SUBSCRIPTION_ID", unique = false, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @Column( name = "USER_ID", unique = false, nullable = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @Column( name = "CHALLENGE_ID", unique = false, nullable = false)
    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
