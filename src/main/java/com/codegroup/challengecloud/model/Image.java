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
    int id;
    String name;
    Date date;
    char size;
    byte[] image;

    @Id
    @GeneratedValue
    @Column(name="IMAGE_ID", unique=true, nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Column(name="IMAGE", nullable = false)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
