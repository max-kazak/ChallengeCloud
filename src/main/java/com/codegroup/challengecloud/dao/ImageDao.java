package com.codegroup.challengecloud.dao;

import java.util.List;

import com.codegroup.challengecloud.model.Image;

/**
 * Created by Nipel-Crumple on 12.03.2015.
 */
public interface ImageDao {
    public void save(Image image);
    public void delete(Image image);
    public void update(Image image);

    public Image getImageById(String imageId);
    public List<Image> findAll(); //Added on 01.04.2015 by Vladimir Zhdanov
}
