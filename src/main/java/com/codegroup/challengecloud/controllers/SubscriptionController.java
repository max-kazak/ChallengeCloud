package com.codegroup.challengecloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yefim on 13.03.2015.
 */
@Controller
public class SubscriptionController {
    @RequestMapping("/subscription")
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("subscription");
    }

    @RequestMapping(value = "/ajaxscroll1", method = RequestMethod.GET)
    public
    @ResponseBody
    String getSubscription() {
        String subscriptionSrc = "<img src='http://www.sunhome.ru/UsersGallery/Cards/prazdnik_den_zemli_kartinka.jpg'>";
        return subscriptionSrc;
    }
}
