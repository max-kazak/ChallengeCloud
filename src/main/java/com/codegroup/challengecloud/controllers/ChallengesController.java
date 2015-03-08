package com.codegroup.challengecloud.controllers;
 
import java.util.Date;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.Random;
 
import com.codegroup.challengecloud.model.Challenge;

/**
* @author Vladimir Zhdanov
* Created 08.03.2015
*
*/
 
@Controller
public class ChallengesController {
 
    @RequestMapping("/challenges")
    public ModelAndView challengesText() {
        return new ModelAndView("challenges", "message", "Challenges");
    }
 
    @RequestMapping(value = "/challengestest", method = RequestMethod.GET)
    public @ResponseBody
    String getTime() {
 
        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. <br> Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return result;
    }
}