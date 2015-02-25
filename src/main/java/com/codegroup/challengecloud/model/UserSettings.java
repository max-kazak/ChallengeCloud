package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Yefim-Krokhin on 23.02.2015.
 */
@Entity
@Table(name = "users", catalog = "challenger", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")
})
public class UserSettings implements Serializable {
    //TODO correct USER_LANG to all user params

    String id;
    String lang;
    User user;

    public UserSettings() {

    }

    @Id
    @Column(name = "USER_ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "USER_LANG", unique = false, nullable = false)
    public String getLang() {
        return lang;
    }

    @Override
    public String toString() {
        return "UserSettings{" +
                "id='" + id + '\'' +
                ", lang='" + lang + '}';
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    public User getUser() {
        return user;
    }
}
