package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
@Entity
@Table(name = "BADGES", catalog = "challenger")
public class Badge implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;
    private Image image;
    private Event event;
    private String condition;

    @Id
    @Column(name = "BADGE_ID", unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @OneToOne
    @JoinColumn(name = "EVENT_ID")
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Column(name = "CONDITION")
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
