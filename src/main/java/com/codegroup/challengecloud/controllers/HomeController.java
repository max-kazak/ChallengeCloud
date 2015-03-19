package com.codegroup.challengecloud.controllers;

import org.apache.log4j.Logger;

import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.services.PostService;
import com.codegroup.challengecloud.services.SubscriptionService;

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

/**
 * Created by Andrey on 19.03.2015.
 */
@Controller
public class HomeController {
	
	private static final Logger log = Logger.getLogger(HomeController.class);
	private static final String TEMPLATE_NAME = "challenge-progress.ftl";
	private List<Subscription> subscriptionList;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("home");
    }
	 
	@RequestMapping(value = "/home-subscriptions", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendAllSubscriptionsToPage(@RequestParam(value = "subscriptionId", required = false) String subscriptionId) {
        log.info("getAllSubscriptions() started");
        /*Default value to report user about server problems*/
        String templateResponse = "<p> Internal Error! </p>";

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HomeController.class, "/");

        Map<String, Object> input = new HashMap<>();
        int numi = Integer.parseInt(subscriptionId);

        StringWriter stringWriter;
        try {
            Template template = configuration.getTemplate(TEMPLATE_NAME);
            stringWriter = new StringWriter();
            try {
                for (int i = 0; i < 5; i++) {
                    input.clear();
                    input.put("subscriptionName", "Subs " + Integer.toString(numi + i));
                    input.put("completion", String.valueOf(i));
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
