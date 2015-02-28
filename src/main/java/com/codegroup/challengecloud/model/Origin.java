package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Yefim on 25.02.2015.
 */
@Entity
@Table(name = "origins", catalog = "challenger"/*, uniqueConstraints = {
        @UniqueConstraint(columnNames = ))
}*/)

//TODO deal with unique fields
public class Origin implements Serializable {
    String id;
    String name;


    Set<Post> posts;

    public Origin() {

    }

    @Id
    @Column(name = "ORIGIN_ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAME", unique = false, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Origins{" +
                "id='" + id + '\'' +
                ", name='" + name + '}';
    }

    @OneToMany(mappedBy = "origins", cascade = CascadeType.ALL)
    public Set<Post> getPosts() {
        return posts;
    }

    /**
     * Set method is neccesarily has to be in @Entity class
     * @author Nipel-Crumple
     */
    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
