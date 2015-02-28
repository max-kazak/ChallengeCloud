package com.codegroup.challengecloud.model;

import java.sql.Date;
import javax.persistence.*;

/**
 * Created on 24.02.2015.
 *
 * @author Andrey
 */
@Entity
@Table(name = "posts",
        schema = "challenger",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "POST_ID")})
public class Post {

    String id;
    //    Origin origin;
    Subscription subscription;
    Date date;
    String url;


    Origin origin;

    public Post() {
    }

    @Id
    @Column(name = "POST_ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    TODO
//    "ManyToOne
//    public Origin getOrigin() {
//    	return origin;
//    }
//    
//    public void setOrigin(Origin origin) {
//    	this.origin = origin;
//    }

    @ManyToOne
    @JoinColumn(name = "SUBSCRIPTION_ID", unique = false, nullable = false)
    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Column(name = "DATE", unique = false, nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "POST_URL", unique = true, nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//  TODO
    @Override
    public String toString() {
        return "Post_id:" + id;
    }


//    TODO check this statement

    /**
     * @author Yefim-Krokhin
     */
    @ManyToOne
    public Origin getOrigin() {
        return origin;
    }

    /**
     * Set method is neccesarily has to be in @Entity class
     * @author Nipel-Crumple
     */
    public void setOrigin(Origin origin) {
        this.origin = origin;
    }
}
