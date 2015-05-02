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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Badge badge = (Badge) o;

        if (!condition.equals(badge.condition)) return false;
        if (!description.equals(badge.description)) return false;
        if (!event.equals(badge.event)) return false;
        if (!id.equals(badge.id)) return false;
        if (!image.equals(badge.image)) return false;
        if (!name.equals(badge.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + event.hashCode();
        result = 31 * result + condition.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", event=" + event +
                ", condition='" + condition + '\'' +
                '}';
    }
}
