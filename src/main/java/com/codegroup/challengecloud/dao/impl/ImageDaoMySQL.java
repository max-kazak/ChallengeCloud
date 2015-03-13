package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.ImageDao;
import com.codegroup.challengecloud.model.Image;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nipel-Crumple on 12.03.2015.
 */
@Repository("imageDao")
public class ImageDaoMySQL extends HibernateDao implements ImageDao {

    private static Logger log = Logger.getLogger(ImageDaoMySQL.class);

    @Override
    public void save(Image image) {
        log.debug("Saving image with id=" + image.getId());
        getSession().save(image);
    }

    @Override
    public void delete(Image image) {
        log.debug("Deleting image with id=" + image.getId());
        getSession().delete(image);
    }

    @Override
    public void update(Image image) {
        log.debug("Updating image with id=" + image.getId());
        getSession().update(image);
    }

    @Override
    public Image getImageById(String imageId) {
        log.debug("Looking for image with id=" + imageId);
        List list = find("from Image where IMAGE_ID = ?", imageId);
        return (Image) list.get(0);
    }
}
