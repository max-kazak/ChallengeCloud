package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image1 = (Image) o;

        if (size != image1.size) return false;
        if (!date.equals(image1.date)) return false;
        if (!id.equals(image1.id)) return false;
        if (!Arrays.equals(image, image1.image)) return false;
        if (!name.equals(image1.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + (int) size;
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
