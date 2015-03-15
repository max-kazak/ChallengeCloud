package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Nipel-Crumple on 12.03.2015.
 */
@Entity
@Table(name = "images", catalog = "challenger")
public class Image implements Serializable {
    String id;
    String name;
    Date date;
    char size;
    byte[] image;

    @Id
    @Column(name="IMAGE_ID", unique=true, nullable=false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="DATE", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name="SIZE", nullable = false)
    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    @Column(name="DATA", nullable = false)
    public byte[] getData() {
        return image;
    }

    public void setData(byte[] image) {
        this.image = image;
    }
}
