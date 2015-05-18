package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.*;
import com.codegroup.challengecloud.services.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by Krokhin on 10.05.2015.
 */
@Controller
public class HistoryController {
    private static final Logger log = Logger.getLogger(HistoryController.class);
    private static final String TEMPLATE_NAME = "history-view.ftl";
    private static final String DELIMITER = "http";
    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;
    @Autowired
    PostService postService;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    BadgeService badgeService;


    @RequestMapping("/history")
    public ModelAndView historyText() {
        Map<String, String> modelMap = new HashMap<String, String>();
        int totalAmount = historyService.findHistoryForUser(userService.getCurrentUser()).size();
        log.debug("totalAmount is " + totalAmount);
        modelMap.put("totalAmount", new Integer(totalAmount).toString());
        return new ModelAndView("history", modelMap);
    }

    @RequestMapping(value = "/history-send", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendHistoryToPage(@RequestParam(value = "numToShow", required = true) String numToShow,
                             @RequestParam(value = "numShown", required = true) String numShown) {
        log.info("sendHistoryToPage() started");
        int numToShowInt = Integer.parseInt(numToShow);
        int numShownInt = Integer.parseInt(numShown);

        /*Default value to report user about server problems*/
        String templateResponse = "<p> Internal Error! </p>";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Set<History> histories = historyService.findHistoryForUser(userService.getCurrentUser());
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HistoryController.class, "/");

        Map<String, Object> input = new HashMap<>();
        StringWriter stringWriter = new StringWriter();

        try {
            Iterator<History> iterator = histories.iterator();
            Template template = configuration.getTemplate(TEMPLATE_NAME);

            for (int i = numShownInt; (i < numShownInt + numToShowInt) && (i < histories.size()); i++) {
                History nextHistory = iterator.next();
                putHistoryIntoMap(input, nextHistory, simpleDateFormat);
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
        log.info("sendHistoryToPage() returns [" + templateResponse + "]");
        return templateResponse;
    }

    private void putHistoryIntoMap(Map<String, Object> input, History tempHistory, SimpleDateFormat simpleDateFormat) {
        String TWITTER_POST_EVENT_ID = "2";
        String ACHIEVEMENT_EVENT_ID = "3";
        String CHALLENGE_COMPLETED_EVENT_ID = "4";
        String SUBSCRIPTION_EVENT_ID = "5";

        log.info("putHistory eventId" + tempHistory.getEvent().getId());

        input.put("date", simpleDateFormat.format(tempHistory.getTimestamp()));

        if (tempHistory.getEvent().getId().equals(TWITTER_POST_EVENT_ID)) {
            Post post = postService.findById(tempHistory.getRefId());
            Subscription subscription = post.getSubscription();
            Challenge challenge = subscription.getChallenge();
            input.put("message", "You created tweet for \"" + challenge.getTitle() + "\" challenge");
            input.put("image", challenge.getImage().getId());
        } else if (tempHistory.getEvent().getId().equals(ACHIEVEMENT_EVENT_ID)) {
            Badge badge = badgeService.findById(tempHistory.getRefId());
            input.put("message", "You got \"" + badge.getName() + "\" achievement");
            input.put("image", badge.getImage().getId());
        } else if (tempHistory.getEvent().getId().equals(CHALLENGE_COMPLETED_EVENT_ID)) {
            Badge badge = badgeService.findById(tempHistory.getRefId());
            input.put("message", "You got \"" + badge.getName() + "\" achievement - complete challenge");
            input.put("image", badge.getImage().getId());
        } else if (tempHistory.getEvent().getId().equals(SUBSCRIPTION_EVENT_ID)){
            Subscription subscription = subscriptionService.findById(tempHistory.getId());
            Challenge challenge = subscription.getChallenge();
            input.put("message", "You subscribed to  \"" + challenge.getTitle() + "\"");
            input.put("image", challenge.getImage().getId());
        }
    }
}
