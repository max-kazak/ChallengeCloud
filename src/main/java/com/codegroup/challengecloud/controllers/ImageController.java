package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.Image;
import com.codegroup.challengecloud.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Nipel-Crumple on 12.03.2015.
 */
@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/images/{imageId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] returnImage(@PathVariable int imageId) {
        Image image = imageService.getImage(imageId);
        return image.getImage();
    }
    /**
    * If you want to check downloading pictures from DB just add this line
     * in every you like page and enjoy (imageId == IMAGE_ID in your DB)
     *   <img src="<c:url value="/images/imageId" />" alt="car_image"/>
     */
}
