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
        public ModelAndView insertImageAjax() {
            return new ModelAndView("challengegroups");
        }

        @RequestMapping(value = "/ajaxscroll", method = RequestMethod.GET)
        public @ResponseBody
        String getImage() {
            String result = "<img src='http://mirgif.com/humor/prikol104.jpg'>";
            return result;
        }
    }

