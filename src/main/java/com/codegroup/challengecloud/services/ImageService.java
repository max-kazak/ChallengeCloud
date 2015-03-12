package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.ImageDao;
import com.codegroup.challengecloud.model.Image;
import com.codegroup.challengecloud.utils.Generator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

/**
 * Created by Nipel-Crumple on 12.03.2015.
 */
@Service("imageService")
public class ImageService {

    private static final Logger log = Logger.getLogger(UserService.class);
    @Autowired
    ImageDao imageDao;

    @Transactional
    public Image createImage(String name, Date date, char size, byte[] image) {
        Image imageEntity = new Image();
        imageEntity.setName(name);
        imageEntity.setDate(date);
        imageEntity.setSize(size);
        imageEntity.setImage(image);
        log.debug("Saving new Image to DB" + imageEntity.getId());
        imageDao.save(imageEntity);

        return imageEntity;
    }

    @Transactional
    public Image getImage(int id) {
        return imageDao.getImageById(id);
    }
}
