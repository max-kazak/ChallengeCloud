package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Yefim-Krokhin on 23.02.2015.
 */
@Entity
/**
 *columnNames should be USER_ID
 * name should be usersettings not users
 * @author Nipel-Crumple
 */
@Table(name = "usersettings", catalog = "challenger", uniqueConstraints = {
        @UniqueConstraint(columnNames = "USER_ID")
})
public class UserSetting implements Serializable {
    //TODO correct USER_LANG to all user params

    String id;
    String lang;
    User user;

    public UserSetting() {

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


    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "UserSetting{" +
                "id='" + id + '\'' +
                ", lang='" + lang + '}';
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    public User getUser() {
        return user;
    }
}
