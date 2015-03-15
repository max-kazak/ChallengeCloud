package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.services.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;


/**
 * Created by Yefim on 13.03.2015.
 */
@Controller
public class SubscriptionController {
    private static final Logger log = Logger.getLogger(Subscription.class);
    private SubscriptionService subscriptionService;
    private Subscription subscription;

    @RequestMapping("/subscription")
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("subscription");
    }

    @RequestMapping(value = "/all-subscriptions", method = RequestMethod.GET)
    public
    @ResponseBody
    String getAllSubscriptions() {
        log.info("getAllSubscriptions() started");
        return new String();
    }
}
