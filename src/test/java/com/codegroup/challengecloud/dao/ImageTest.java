package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Image;
import com.codegroup.challengecloud.services.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

/**
 * Created by Nipel-Crumple on 12.03.2015.
 * This test should work in your IDE
 * because of identity of DB scripts
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ImageTest {
    private final String IMAGE_ID = "k9f4976e1281a910";
    private final String IMAGE_NAME = "sport";
    @Autowired
    ImageService imageService;

    @Test
    public void getImageTest() {
        Image image = imageService.getImage(IMAGE_ID);
        Assert.assertNotNull(image);
        Assert.assertEquals(image.getId(), IMAGE_ID);
        Assert.assertEquals(image.getName(), IMAGE_NAME);
    }
}
