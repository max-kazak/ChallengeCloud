package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.constants.UserRoles;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WelcomeController {

    private static final Logger log = Logger.getLogger(WelcomeController.class);

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String showWelcome(ModelMap model) {
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showDefault(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            log.debug("user " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + " is signed in: redirecting to home page");
            return "redirect:/home";
        }
        else
            return "welcome";
    }
}
