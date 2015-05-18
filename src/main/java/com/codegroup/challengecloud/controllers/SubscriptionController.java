package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.services.TwitterDownloadService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Created by Yefim Krokhin on 13.03.2015.
 */
@Controller
public class SubscriptionController {
    private static final Logger log = Logger.getLogger(SubscriptionController.class);
    private static final String TEMPLATE_NAME = "subscription-view.ftl";
    private static final String DELIMITER = "http";
    private String subscriptionId;
    @Resource
    TwitterDownloadService twitterDownloadService;

    @RequestMapping("/subscription")
    public ModelAndView subscriptionText(@RequestParam(value = "subscriptionId", required = true) String subscriptionId) {
        Map<String, String> modelMap = new HashMap<String, String>();
        setSubscriptionId(subscriptionId);
        int totalAmount = twitterDownloadService.downloadTweetsForSubscriptionPage(subscriptionId).size();
        log.debug("totalAmount is " + totalAmount);
        modelMap.put("totalAmount", new Integer(totalAmount).toString());
        return new ModelAndView("subscription", modelMap);
    }

    @RequestMapping(value = "/subscription-send", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendAllPostsToPage(@RequestParam(value = "numToShow", required = true) String numToShow,
                              @RequestParam(value = "numShown", required = true) String numShown) {
        log.info("sendAllPostsToPage() started");
        int numToShowInt = Integer.parseInt(numToShow);
        int numShownInt = Integer.parseInt(numShown);

        /*Default value to report user about server problems*/
        String templateResponse = "<p> Internal Error! </p>";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Set<Tweet> tweets = twitterDownloadService.downloadTweetsForSubscriptionPage(getSubscriptionId());

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SubscriptionController.class, "/");

        Map<String, Object> input = new HashMap<>();

        StringWriter stringWriter = new StringWriter();
        try {
            Iterator<Tweet> iterator = tweets.iterator();
            Template template = configuration.getTemplate(TEMPLATE_NAME);
            for (int i = numShownInt; (i < numShownInt + numToShowInt) && (i < tweets.size()); i++) {
                Tweet nextTweet = iterator.next();
                putTweetsIntoMap(input, nextTweet, simpleDateFormat);
                template.process(input, stringWriter);
            }
        } catch (IOException e) {
            log.error("Can't create a template");
        } catch (TemplateException e) {
            log.error("Can't create a template");
        } finally {
            try {
                stringWriter.close();
            } catch (IOException e) {
                log.debug("can't close stringWriter");
            }
            templateResponse = stringWriter.toString();
        }
        log.info("sendAllPostsToPage() returns [" + templateResponse + "]");
        return templateResponse;
    }

    private void putTweetsIntoMap(Map<String, Object> input, Tweet tempTweet, SimpleDateFormat simpleDateFormat) {
        String marker = "http://t.co/";
        if (tempTweet.hasMedia()) {
            input.put("postImage", tempTweet.getEntities().getMedia().get(0).getMediaUrl());
            log.debug("Tweet media to add " + tempTweet.getEntities().getMedia().get(0).getMediaUrl());
        } else {
            input.put("postImage", tempTweet.getUser().getProfileImageUrl());
        }

        if(tempTweet.getUnmodifiedText().contains(marker)) {
            input.put("postText", tempTweet.getUnmodifiedText().substring(0, tempTweet.
                    getUnmodifiedText().lastIndexOf(DELIMITER)));
        } else {
            input.put("postText", tempTweet.getText());
        }
        input.put("nick", tempTweet.getUser().getScreenName());
        input.put("originPost", tempTweet.getUser().getProfileUrl() + "/status/" + tempTweet.getId());

        input.put("postDate", simpleDateFormat.format(tempTweet.getCreatedAt()));
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        log.debug("subscriptionId is " + subscriptionId);
        this.subscriptionId = subscriptionId;
    }
}
