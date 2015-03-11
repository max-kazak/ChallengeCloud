package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "welcome";
    }

}
