package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Krokhin on 15.04.2015.
 */
@Entity
@Table(name = "events", catalog = "challenger", uniqueConstraints = {
        @UniqueConstraint(columnNames = "EVENT_ID")
})
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;
    String name;
    String clazz;

    public Event() {

    }

    @Id
    @Column(name = "EVENT_ID", unique = true, nullable = false)
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

    @Column(name = "CLASS", unique = false, nullable = false)
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Event{id='" + id + "\'" +
                ", name='" + name + "\'" +
                ", class='" + clazz +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!clazz.equals(event.clazz)) return false;
        if (!id.equals(event.id)) return false;
        if (!name.equals(event.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + clazz.hashCode();
        return result;
    }
}

