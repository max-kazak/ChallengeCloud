package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.ImageDao;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.Image;
import com.codegroup.challengecloud.utils.Generator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


/**
 * Created by Nipel-Crumple on 12.03.2015.
 */
@Service("imageService")
public class ImageService {

    private static final Logger log = Logger.getLogger(UserService.class);
    @Autowired
    ImageDao imageDao;

    @Transactional
    public Image createImage(String name,char size, byte[] data) {
        Image imageEntity = new Image();
        imageEntity.setId(Generator.generateId());
        imageEntity.setName(name);
        imageEntity.setDate(new Date((new java.util.Date()).getTime()));
        imageEntity.setSize(size);
        imageEntity.setData(data);
        log.debug("Saving new Image to DB" + imageEntity.getId());
        imageDao.save(imageEntity);

        return imageEntity;
    }

    @Transactional
    public Image getImage(String id) {
        return imageDao.getImageById(id);
    }

    /**
     * Added on 01.04.2015 by Vladimir Zhdanov
     * @return List of all images
     */
    @Transactional
    public List<Image> findAll() {
    		return imageDao.findAll();
    }
}
