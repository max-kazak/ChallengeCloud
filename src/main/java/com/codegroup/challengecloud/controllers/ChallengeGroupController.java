package com.codegroup.challengecloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Random;

/**
 * Created by Nipel-Crumple on 09.03.2015.
 */
@Controller
public class ChallengeGroupController {

        @RequestMapping("/challengegroups")
        public ModelAndView helloAjaxTest() {
            return new ModelAndView("ajax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
        }

        @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
        public @ResponseBody
        String getTime() {
            Random rand = new Random();
            float r = rand.nextFloat() * 100;
            String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
            System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
            return result;
        }
    }

