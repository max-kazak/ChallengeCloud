package com.codegroup.challengecloud.controllers;

import org.apache.log4j.Logger;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import org.springframework.social.connect.Connection;

/**
 * Created by Yefim on 16.03.2015.
 */
@Controller
public class SettingsController {
    private static final Logger log = Logger.getLogger(SettingsController.class);

    @Inject
    ConnectionRepository connectionRepository;

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String showSettings(ModelMap model){
        String[] providerSettings = getProviderSettings("org.springframework.social.twitter.api.Twitter");
        if (providerSettings != null) {
            model.addAttribute("twitterName", providerSettings[0]);
            model.addAttribute("profileUrl", providerSettings[1]);
            model.addAttribute("isConnectedToTwitter", providerSettings[2]);
            model.addAttribute("imgSrc", providerSettings[3]);
        } else {
            log.debug("WHAT THE FUCK IS GOING ON?");
        }
        return "settings";
    }

    public String[] getProviderSettings(String providerId) {
        Connection<?> providerConnection = null;
        String[] param = null;
        try {
            Class providerClass = Class.forName(providerId);
            providerConnection = connectionRepository.findPrimaryConnection(providerClass);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        if (providerConnection != null) {
            param = new String[4];
            param[0] = providerConnection.getDisplayName();
            log.debug("providerDisplay: " + param[0]);
            param[1] = providerConnection.getProfileUrl();
            log.debug("providerProfileUrl: " + param[1]);
            param[2] = providerConnection.test() ? "true" : "false";
            log.debug("isConnected: " + param[2]);
            param[3] = providerConnection.getImageUrl();
            log.debug("providerImgSrc: " + param[3]);
            return param;
        } else {
            return param;
        }
    }


}
