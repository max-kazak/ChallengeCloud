package com.codegroup.challengecloud.controllers;

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
    private String subscriptionId;
    @Resource
    TwitterDownloadService twitterDownloadService;

    @RequestMapping("/subscription")
    public ModelAndView subscriptionText(@RequestParam(value = "subscriptionId", required = false) String subscriptionId) {
        setSubscriptionId(subscriptionId);
        return new ModelAndView("subscription", "message", "Subscriptions");
    }

    @RequestMapping(value = "/subscription-send", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendAllPostsToPage() {
        log.info("sendAllPostsToPage() started");
        /*Default value to report user about server problems*/
        String templateResponse = "<p> Internal Error! </p>";

        Set<Tweet> tweets = twitterDownloadService.downloadTweetsForSubscriptionPage(getSubscriptionId());

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SubscriptionController.class, "/");

        Map<String, Object> input = new HashMap<>();

        StringWriter stringWriter = new StringWriter();
        try {
            Template template = configuration.getTemplate(TEMPLATE_NAME);
            for (Iterator<Tweet> iterator = tweets.iterator(); iterator.hasNext(); ) {
                Tweet nextTweet = iterator.next();
                if (nextTweet.hasMedia()) {
                    input.put("postImage", nextTweet.getEntities().getMedia().get(0).getMediaUrl());
                    log.debug("Tweet media to add " + nextTweet.getEntities().getMedia().get(0).getMediaUrl());
                }
                input.put("postText", nextTweet.getText());
                input.put("postDate", nextTweet.getCreatedAt().toString());
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
        log.info("sendAllPostsToPage() returns [" + templateResponse + "].");
        return templateResponse;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
