package com.codegroup.challengecloud.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Yefim on 16.03.2015.
 */
@Controller
public class SettingsController {
    private static final Logger log = Logger.getLogger(SettingsController.class);

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String showSettings(ModelMap model){
        return "settings";
    }
}
