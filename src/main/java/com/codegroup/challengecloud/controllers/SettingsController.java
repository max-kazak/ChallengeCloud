package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.UserService;
import com.codegroup.challengecloud.utils.Generator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yefim on 16.03.2015.
 */
@Controller
public class SettingsController {
    private static final Logger log = Logger.getLogger(SettingsController.class);

    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    UserService userService;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String showSettings(ModelMap model) {

        User userToUpdate = userService.getCurrentUser();
        log.debug("current user = " + userToUpdate.getId() + " " + userToUpdate.getPassword());
        if (userToUpdate != null) {
            model.addAttribute("CCloudName", userToUpdate.getName());
            model.addAttribute("CCloudEmail", userToUpdate.getEmail());
            model.addAttribute("CCloudLogin", userToUpdate.getLogin());
            log.debug("Password in field " + userToUpdate.getPassword());
            model.addAttribute("CCloudPassword", userToUpdate.getPassword());
            log.debug("Model attr password: " + model.get("CCloudPassword"));
        }

        List providerSettings = getProviderSettings("org.springframework.social.twitter.api.Twitter");
        if (providerSettings != null) {
            model.addAttribute("twitterName", providerSettings.get(0));
            model.addAttribute("profileUrl", providerSettings.get(1));
            model.addAttribute("isConnectedToTwitter", providerSettings.get(2));
            model.addAttribute("imgSrc", providerSettings.get(3));
        } else {
            log.debug("Cannot get provider settings");
        }

        return "settings";
    }

    public List getProviderSettings(String providerId) {
        Connection<?> providerConnection = null;
        List param = null;
        try {
            Class providerClass = Class.forName(providerId);
            providerConnection = connectionRepository.findPrimaryConnection(providerClass);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        if (providerConnection != null) {
            param = new ArrayList();
            param.add(0, providerConnection.getDisplayName());
            log.debug("providerDisplay: " + param.get(0));
            param.add(1, providerConnection.getProfileUrl());
            log.debug("providerProfileUrl: " + param.get(1));
            param.add(2, providerConnection.test() ? "true" : "false");
            log.debug("isConnected: " + param.get(2));
            param.add(3, providerConnection.getImageUrl());
            log.debug("providerImgSrc: " + param.get(3));
            return param;
        } else {
            return param;
        }
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String updateUserProfile(@ModelAttribute("user") User user, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            User currentUser = userService.getCurrentUser();
            currentUser.setEmail(user.getEmail());
            currentUser.setLogin(user.getLogin());
            currentUser.setName(user.getName());
            String password = user.getPassword();

            if (password != null) {
                currentUser.setPassword(Generator.generateHashedPass(password));
            }

            log.info("Updating user's profile with id = " + user.getId());
            userService.updateProfile(currentUser);
            return "redirect:/settings";
        } else {
            log.warn("Cannot update profile wit login: " + user.getLogin());
            return "settings";
        }
    }


}
