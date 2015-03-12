package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.Image;
import com.codegroup.challengecloud.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by Nipel-Crumple on 12.03.2015.
 */
public class ImageController {
    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/images/{imageId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] returnImage(@PathVariable int imageId) {
        Image image = imageService.getImage(imageId);
        return image.getImage();
    }

}
