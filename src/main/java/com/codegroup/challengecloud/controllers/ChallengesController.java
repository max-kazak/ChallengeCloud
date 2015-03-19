package com.codegroup.challengecloud.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


//import com.codegroup.challengecloud.model.User;
//import com.codegroup.challengecloud.services.UserService;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.services.ChallengeService;








/* For FreeMarker */
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * @author Vladimir Zhdanov
 *         Created 08.03.2015
 */

@Controller
public class ChallengesController {
    private static final Logger log = Logger.getLogger(ChallengesController.class);
    private Challenge challenge;
    private ChallengeService challengeService;

    @RequestMapping("/challenges")
    public ModelAndView challengesText() {
        return new ModelAndView("challenges", "message", "Challenges");
    }

    /*TODO delete unused test*/
    @RequestMapping(value = "/challengestest", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTime() {

        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. <br> Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return result;
    }

    @RequestMapping(value = "/challenges-all", method = RequestMethod.GET)
    public
    @ResponseBody
    String getAllChallenges(
            @RequestParam(value = "num", required = false) String num
    ) throws Exception {
        log.info("getAllChallenges().");
        String code = "<p> Internal Error! </p>";
        int numi = Integer.parseInt(num);

        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(ChallengesController.class, "/");// TODO /templates

        Map<String, Object> input = new HashMap<String, Object>();

        StringWriter stringWriter;
        try {
            Template template = cfg.getTemplate("challenge-view.ftl");
            stringWriter = new StringWriter();
            try {
                for (int i = 0; i < 5; i++) {
                    input.clear();
                    input.put("challengeName", "Challenge " + Integer.toString(numi + i));
                    input.put("challengeDescription", "Challenge Description " + Integer.toString(numi + i));
                    template.process(input, stringWriter);
                }
            } catch (TemplateException e2) {
                log.error("Template Exception.");
                //throw new Exception("ChallengesController.getAllChallenges: TemplateExcepon: "+e2.toString());
            } finally {
                stringWriter.close();
                code = stringWriter.toString();
            }
        } catch (IOException e) {
            log.error("Can't load template.");
            //throw new IOException("ChallengesController.getAllChallenges: Can't load template challenge.ftl: "+e.toString());
        }
        log.info("getAllChallenges() returns [" + code + "].");
        return code;
    }
}