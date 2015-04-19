package com.codegroup.challengecloud.model;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Created by Nipel-Crumple on 18.04.2015.
 */
@Entity
@Table(name = "badges", catalog = "challenger")
public class Badge implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;
    private Image image;
    private Event event;
    private String condition;

    @Id
    @Column(name = "BADGE_ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToOne
    @JoinColumn(name = "IMAGE_ID", nullable = false)
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    @ManyToOne
    @JoinColumn(name = "EVENT_ID", nullable = false)
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Column(name = "CONDITION", nullable = false)
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
