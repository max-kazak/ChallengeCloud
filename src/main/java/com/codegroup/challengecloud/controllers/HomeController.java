package com.codegroup.challengecloud.controllers;

import org.apache.log4j.Logger;

import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.services.ChallengeService;
import com.codegroup.challengecloud.services.PostService;
import com.codegroup.challengecloud.services.SubscriptionService;
import com.codegroup.challengecloud.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * Created by Andrey on 19.03.2015.
 */
@Controller
public class HomeController {

    private static final Logger log = Logger.getLogger(HomeController.class);
    private static final String TEMPLATE_NAME = "challenge-progress.ftl";

    @Resource
    private SubscriptionService subscriptionService;

    private List<Subscription> subscriptions;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        log.info("Getting a default home page");
        Map<String, String> modelMap = new HashMap<String, String>();
        String total_num;
        try {
            log.debug("Trying to get number of subscriptions");
            subscriptions = subscriptionService.findForCurrentUser();
            total_num = Integer.toString(subscriptions.size());
            log.debug("Success! Got:" + total_num + " subscriptions");
        } catch (NullPointerException e) {
            log.error("Couldn't get any subscriptions", e);
            total_num = Integer.toString(0);
        }
        modelMap.put("total_num", total_num);
        log.debug("Number of subscriptions is set to: " + total_num);

        return new ModelAndView("home", modelMap);
    }

    private void putSubscriptionIntoMap(Map<String, Object> input, Subscription subscription) {
        String subscriptionName = subscription.getChallenge().getTitle();
        String date = subscription.getDate().toString();
        String subscriptionId = subscription.getId();
        input.put("subscriptionName", "Challenge - " + subscriptionName);
        input.put("date", date);
        /*Added by Yefim Krokhin on 02.04.2015*/
        input.put("subscriptionId", subscriptionId);
    }

    @RequestMapping(value = "/home-subscriptions", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendAllSubscriptionsToPage(@RequestParam(value = "numToShow", required = true) String numToShow,
                                      @RequestParam(value = "numShown", required = true) String numShown) {
        log.info("Getting subscriptions for home page. numToShow=" + numToShow + ", numShown=" + numShown);
        log.debug("Getting list of subscriptions for current user");
        List<Subscription> subscriptions = subscriptionService.findForCurrentUser();

        /*Default value to report user about server problems*/
        String templateResponse = "<p> Internal Error! </p>";

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HomeController.class, "/");

        Map<String, Object> input = new HashMap<>();
        int numToShowInt = Integer.parseInt(numToShow);
        int numShownInt = Integer.parseInt(numShown);

        StringWriter stringWriter;

        log.debug("Trying to get numToShowInt=" + numToShowInt + " challenges for current user on home page. Starting from"
                + "numShownInt=" + numShownInt);
        try {
            Template template = configuration.getTemplate(TEMPLATE_NAME);
            stringWriter = new StringWriter();
            try {
                for (int i = numShownInt; (i < numShownInt + numToShowInt) && (i < subscriptions.size()); i++) {
                    log.debug("Adding subscription No." + i + " to map");
                    input.clear();
                    putSubscriptionIntoMap(input, subscriptions.get(i));
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
        }
        log.info("sendAllSubscriptionsToPage() returns [" + templateResponse + "].");
        return templateResponse;
    }
}
