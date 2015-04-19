package com.codegroup.challengecloud.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codegroup.challengecloud.model.Image;
import com.codegroup.challengecloud.services.ChallengeService;
import com.codegroup.challengecloud.services.ImageService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * Created on 09.04.2015
 * @author Andrey
 */
@Controller
public class ChallengeCreationController {
	private static final Logger log = Logger.getLogger(ChallengeCreationController.class);
	
    @Resource
    private ChallengeService challengeService;
    @Resource
    private ImageService imageService;
    
    @RequestMapping("/challenge_creation")
    public
    @ResponseBody
    ModelAndView challengeCreation() {
    	Map <String,String> map = new HashMap<String,String>();
    	map.put("totalAmount", Integer.toString(imageService.findAll().size()) );
    	map.put("message", "Challenge Creation");
    	log.info("Returning default page for challenge creation. Number of images:"+map.get("totalAmount"));
    	return new ModelAndView("challenge_creation", map);
    }
    
    private void putImage(Map<String, Object> input, Image image) {
        input.put("imageId", image.getId());
        input.put("imageName", image.getName());
        DateFormat df = new SimpleDateFormat("DD/MM YYYY");
        input.put("imageDate", df.format(image.getDate()));
    }
    
    @RequestMapping(value = "/challenge_creation_images", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendImagesToPage(@RequestParam(value = "numToShow", required = true) String numToShow,
                              @RequestParam(value = "numShown", required = true) String numShown) {
        log.info("sendImagesToPage() started");
        int numToShowInt = Integer.parseInt(numToShow);
        int numShownInt = Integer.parseInt(numShown);

        /*Default value to report user about server problems*/
        String templateResponse = "<p> Internal Error! </p>";

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SubscriptionController.class, "/");

        Map<String, Object> input = new HashMap<>();

        StringWriter stringWriter = new StringWriter();
        
        List<Image> images = imageService.findAll();
        
        try {
            Iterator<Image> iterator = images.iterator();
            Template template = configuration.getTemplate("image_show.ftl");
            log.debug("Putting images into map");
            for (int i = numShownInt; (i < numShownInt + numToShowInt) && (i < images.size()); i++) {
                Image nextImage = iterator.next();
                putImage(input, nextImage);
                template.process(input, stringWriter);
            }
        } catch (IOException e) {
            log.error("Can't create a template",e);
        } catch (TemplateException e) {
            log.error("Can't create a template",e);
        } finally {
            try {
                stringWriter.close();
            } catch (IOException e) {
                log.debug("Can't close stringWriter",e);
            }
            templateResponse = stringWriter.toString();
        }
        log.info("sendImagesToPage() returns [" + templateResponse + "].");
        return templateResponse;
    }
    
    @RequestMapping("/create_challenge")
    public
    @ResponseBody
    String challengeCreation(
    		@RequestParam(value = "title", required = true) String title,
    		@RequestParam(value = "description", required = true) String description,
    		@RequestParam(value = "diff", required = true) Integer difficulty,
    		@RequestParam(value = "hash", required = true) String hashtag,
    		@RequestParam(value = "imageId", required = true) String imageId) {
    	log.info("Creating a challenge with parameters: "
    			+ "\n Title: " + title
    			+ "\n Description: " + description
    			+ "\n Difficulty:" + difficulty
    			+ "\n Hashtag: " +hashtag
    			+ "\n Image ID: " +imageId);
    	try {
    		challengeService.createChallenge(title, description, difficulty, hashtag, imageService.getImage(imageId));
    		return "Success!";
    	} catch(Exception e) {
    		log.error("An error occured",e);
    		return "Failed to create a challenge";
    	}
    }
}
