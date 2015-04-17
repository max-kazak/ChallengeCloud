package com.codegroup.challengecloud.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.Image;
import com.codegroup.challengecloud.services.ImageService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;









/* For FreeMarker */
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Vladimir Zhdanov
 *         Created 28.03.2015
 */

@Controller
public class ImagesController {
    private static final Logger log = Logger.getLogger(ImagesController.class);
    @Resource
    private ImageService imageService;

    @RequestMapping("/images-managing")
    public ModelAndView imagesManagingText() {
    	Map <String,String> map = new HashMap<String,String>();
    	map.put("message", "Images"); 
    	map.put("max_count_html", Integer.toString(imageService.findAll().size()) );
    	map.put("uploadUrl", "/challengecloud/images-managing-upload.html");
        //return new ModelAndView("images-managing", "message", "Images");
        return new ModelAndView("images-managing", map);
    }
    
    //TODO: make it jsp, not ftl. and use <c:url...>
    // jsp does'n work, so I can't use <c:url...> 17.04.2015 by Vladimir Zhdanov
    private static final String IMAGES_MANAGING_URL = "/challengecloud/images-managing.html";

    /**
     * @author Vladimir Zhdanov
     * created on 01.04.2015
     * This method puts values of given image into map. Field names you can find in .ftl file.
     * @param Map<String, Object> Map, in witch items are put
     * @param Image from that entity information is taken
     */
    private void putImage(Map<String, Object> input, Image image) {
        input.put("imageId", image.getId());
        input.put("imageName", image.getName());
        //TODO: It is not thread safe, that's why is not optimized
        DateFormat df = new SimpleDateFormat("d MM, yyyy");
        input.put("imageDate", df.format(image.getDate()));
    }
    
    @RequestMapping(value = "/images-managing-all", method = RequestMethod.GET)
    public
    @ResponseBody
    String getAllImages(
            @RequestParam(value = "num", required = true) String num,
            @RequestParam(value = "count", required = true) String count) {
        log.info("getAllImages()");
        String code = "<p> Internal Error! </p>";
        int numi = Integer.parseInt(num);
        int counti = Integer.parseInt(count);
        Map<String, Object> input = new HashMap<String, Object>();
        List<Image> images = imageService.findAll();
        StringWriter stringWriter;
        Configuration cfg = new Configuration();
        
        cfg.setClassForTemplateLoading(ImagesController.class, "/");// TODO /templates
        
        try {
            Template template = cfg.getTemplate("image-managing.ftl");
            stringWriter = new StringWriter();
            try {
        		// i is from 1 to images.size()
        		// If we want second element, we should have at least two
                for (int i = numi; ((i < numi + counti) && (i <= images.size())) ; i++) {
                    input.clear();
                    putImage(input, images.get(i-1));// Very important!
                    template.process(input, stringWriter);
                }
            } catch (TemplateException e2) {
                log.error("Template Exception.");
            } finally {
                stringWriter.close();
                code = stringWriter.toString();
            }
        } catch (IOException e) {
            log.error("Can't load template: IOException.");
        }
        //log.info("getAllImages() returns [" + code + "].");
        return code;
    }

    /**
     * This method returns images-managing-upload page as String, because I can't return ModelAndView, just String
     * @param text
     * @return
     */
    String imagesManagingUploadPage(String text) {
        log.info("imagesManagingUploadPage()");
        String code = "<p> Internal Error! </p>";
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("images_managing", IMAGES_MANAGING_URL);
        StringWriter stringWriter;
        Configuration cfg = new Configuration();
        
        cfg.setClassForTemplateLoading(ChallengesController.class, "/");// TODO /templates
        
        try {
            Template template = cfg.getTemplate("images-managing-upload.ftl");
            stringWriter = new StringWriter();
            try {
                //input.clear();
                input.put("upload_status", text);
                template.process(input, stringWriter);
            } catch (TemplateException e2) {
                log.error("Template Exception.");
            } finally {
                stringWriter.close();
                code = stringWriter.toString();
            }
        } catch (IOException e) {
            log.error("Can't load template: IOException.");
        }
        return code;
    }
    
    // Example https://spring.io/guides/gs/uploading-files/
    // Created on 09.04.2015 by Vladimir Zhdanov
    @RequestMapping(value="/images-managing-upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "/images-managing-upload", method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("file") MultipartFile file){
    	Map <String,String> map = new HashMap();
    	// Because ModelAndView for unknown reason doesn't work, let's use FreeMarker:   
    	if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                //TODO What to do with size???
                log.debug("Before saving an image");
                imageService.createImage(file.getName(), 'S', bytes);
                return imagesManagingUploadPage("You successfully uploaded " + file.getName() + "!");
            } catch (Exception e) {
                return imagesManagingUploadPage("You failed to upload " + file.getName() + " => " + e.getMessage());
            }
        } else {
        	return imagesManagingUploadPage("You failed to upload " + file.getName() + " because the file was empty."); 
        }
    }
    
    /**
     * Created on 17.04.2015 by Vladimir Zhdanov. This method for editing parameters of one image
     * @param num
     * @param count
     * @return String "You successfully edited Image" or "Sorry, but something gone wrong"
     */
    @RequestMapping(value = "/images-managing-edit", method = RequestMethod.GET)
    public
    @ResponseBody
    String editImage(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "name", required = true) String name) {
        log.info("editImage()");
        Image image;
        image = imageService.getImage(id);
        if (image != null) {
        	image.setName(name);
        	imageService.updateImage(image);
        	return "You successfully edited Image!";
        } else {
        	return "Sorry, but something gone wrong";
        }
    }
    
    /**
     * Created on 17.04.2015 by Vladimir Zhdanov. This method deleting one Image
     * @param num
     * @param count
     * @return String "You successfully edited Image" or "Sorry, but something gone wrong"
     */
    @RequestMapping(value = "/images-managing-delete", method = RequestMethod.GET)
    public
    @ResponseBody
    String deleteImage(
            @RequestParam(value = "id", required = true) String id) {
        log.info("deleteImage()");
        Image image;
        image = imageService.getImage(id);
        if (image != null) {
        	imageService.deleteImage(image);
        	return "You successfully deleted Image! (!)";
        } else {
        	return "Sorry, but something gone wrong";
        }
    }
}