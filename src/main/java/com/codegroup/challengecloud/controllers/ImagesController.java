package com.codegroup.challengecloud.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Example https://spring.io/guides/gs/uploading-files/
    // Created on 09.04.2015 by Vladimir Zhdanov
    @RequestMapping(value="/images-managing-upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "/images-managing-upload", method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("file") MultipartFile file){
    	if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                //TODO What to do with size???
                log.debug("Befre saving an image");
                imageService.createImage(file.getName(), 'S', bytes);
                return "You successfully uploaded " + file.getName() + "!";
            } catch (Exception e) {
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getName() + " because the file was empty.";
        }
        }
}