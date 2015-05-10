package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.Badge;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.HistoryService;
import com.codegroup.challengecloud.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

/**
 * Created by Nipel-Crumple on 09.05.2015.
 */
@Controller
public class TrophyController {
    private static final Logger log = Logger.getLogger(TrophyController.class);
    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;

    @RequestMapping(value = "/trophy", method = RequestMethod.GET)
    public String trophy() {
        return "/trophy";
    }


    @RequestMapping(value = "/gettrophies", method = RequestMethod.GET)
    public @ResponseBody String showAchievements() {
        log.info("ShowAchievements launches");
        User user = userService.getCurrentUser();
        Set<Badge> userBadges = historyService.getUserBadges(user);
        StringBuffer response = new StringBuffer();
        for (Badge badge : userBadges) {
            response.append("<div style='height:128px'>");
            response.append("<img style='height:inherit; width:128px;'class='col-md-4' src=\'./images/" + badge.getImage().getId() + "\' >");
            response.append("<h2 class='col-md-8'>");
            response.append(badge.getName());
            response.append("</h2>");
            response.append("<p style='font-size: 16px' class='col-md-8'>");
            response.append(badge.getDescription());
            response.append("</p>");
            response.append("</div>");
        }
        log.info("RESPONSE FROM TROPHYCONTROLLER: " + response.toString());
        return response.toString();
    }
}
