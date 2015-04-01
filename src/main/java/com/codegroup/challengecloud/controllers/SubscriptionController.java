package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.services.PostService;
import com.codegroup.challengecloud.services.SubscriptionService;
import com.codegroup.challengecloud.services.TwitterDownloadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TweetData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;


/**
 * Created by Yefim on 13.03.2015.
 */
@Controller
public class SubscriptionController {
    private static final Logger log = Logger.getLogger(SubscriptionController.class);
    private static final String TEMPLATE_NAME = "subscription-view.ftl";
    private List<Post> postList;

    @Resource
    TwitterDownloadService twitterDownloadService;

    @RequestMapping("/subscription")
    public ModelAndView subscriptionText() {
        return new ModelAndView("subscription", "message", "Subscriptions");
    }

    @RequestMapping(value = "/subscription-send", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendAllPostsToPage(@RequestParam(value = "subscriptionId", required = false) String subscriptionId) {
        log.info("sendAllPostsToPage() started");
        /*Default value to report user about server problems*/
        String templateResponse = "<p> Internal Error! </p>";

        Set<Tweet> tweets = twitterDownloadService.downloadTweetsForSubscriptionPage();

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SubscriptionController.class, "/");

        Map<String, Object> input = new HashMap<>();
        int numi = Integer.parseInt(subscriptionId);


        StringWriter stringWriter = new StringWriter();
        try {
            Template template = configuration.getTemplate(TEMPLATE_NAME);
            for(Iterator<Tweet> iterator = tweets.iterator(); iterator.hasNext();) {
                Tweet nextTweet = iterator.next();
                if(nextTweet.hasMedia()) {
                    input.put("postImage", nextTweet.getEntities().getMedia().get(0));
                    log.debug("Tweet media to add " + nextTweet.getEntities().getMedia().get(0));
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



       /* try {
            Template template = configuration.getTemplate(TEMPLATE_NAME);
            stringWriter = new StringWriter();
            try {
                for (int i = 0; i < 5; i++) {
                    input.clear();
                    input.put("subscriptionName", "Subs " + tweets.get(1).getId());
                    input.put("subscriptionDescription", "Subs   Description "  + "LOL");
                    template.process(input, stringWriter);
                }
            } catch (TemplateException e2) {
                log.error("Template Exception.");
            } finally {
                stringWriter.close();
                templateResponse = stringWriter.toString();
            }
        } catch (IOException e) {
            log.error("Can't load template.");
        }*/
        log.info("sendAllPostsToPage() returns [" + templateResponse + "].");
        return templateResponse;
    }
}
