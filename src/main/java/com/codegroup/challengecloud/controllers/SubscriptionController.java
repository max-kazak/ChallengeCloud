package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.services.PostService;
import com.codegroup.challengecloud.services.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

import java.util.List;


/**
 * Created by Yefim on 13.03.2015.
 */
@Controller
public class SubscriptionController {
    private static final Logger log = Logger.getLogger(Subscription.class);
    private List<Post> postList;

    private void getAllPostsFromDB() {
        PostService postService = new PostService();

    }

    @RequestMapping(value = "/subscription", method = RequestMethod.GET)
    public String sendAllPostsToPage(@ModelAttribute("model") ModelMap model) {
        log.info("getAllSubscriptions() started");
        model.addAttribute("postList", postList);
        return new String();
    }
}
