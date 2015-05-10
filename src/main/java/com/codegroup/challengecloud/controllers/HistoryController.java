package com.codegroup.challengecloud.controllers;

import com.codegroup.challengecloud.model.History;
import com.codegroup.challengecloud.services.HistoryService;
import com.codegroup.challengecloud.services.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Krokhin on 10.05.2015.
 */
@Controller
public class HistoryController {
    private static final Logger log = Logger.getLogger(HistoryController.class);
    private static final String TEMPLATE_NAME = "history-view.ftl";
    private static final String DELIMITER = "http";
    @Resource
    UserService userService;
    @Resource
    HistoryService historyService;

    @RequestMapping("/history")
    public ModelAndView historyText() {
        Map<String, String> modelMap = new HashMap<String, String>();
        int totalAmount = historyService.findHistoryForUser(userService.getCurrentUser()).size();
        log.debug("totalAmount is " + totalAmount);
        modelMap.put("totalAmount", new Integer(totalAmount).toString());
        return new ModelAndView("history", modelMap);
    }

    @RequestMapping(value = "/history-send", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendHistoryToPage(@RequestParam(value = "numToShow", required = true) String numToShow,
                             @RequestParam(value = "numShown", required = true) String numShown) {
        log.info("sendHistoryToPage() started");
        int numToShowInt = Integer.parseInt(numToShow);
        int numShownInt = Integer.parseInt(numShown);

        /*Default value to report user about server problems*/
        String templateResponse = "<p> Internal Error! </p>";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Set<History> histories = historyService.findHistoryForUser(userService.getCurrentUser());
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HistoryController.class, "/");

        Map<String, Object> input = new HashMap<>();
        StringWriter stringWriter = new StringWriter();

        try {
            Iterator<History> iterator = histories.iterator();
            Template template = configuration.getTemplate(TEMPLATE_NAME);

            for (int i = numShownInt; (i < numShownInt + numToShowInt) && (i < histories.size()); i++) {
                History nextHistory = iterator.next();
                putHistoryIntoMap(input, nextHistory, simpleDateFormat);
                template.process(input, stringWriter);
            }
        } catch (IOException e) {
            log.error("Can't create a template");
        } catch (TemplateException e) {
            log.error("Can't create a template");
        } finally {
            try {
                stringWriter.close();
            } catch (IOException e) {
                log.debug("can't close stringWriter");
            }
            templateResponse = stringWriter.toString();
        }
        log.info("sendHistoryToPage() returns [" + templateResponse + "]");
        return  templateResponse;
    }

    private void putHistoryIntoMap(Map<String, Object> input, History tempHistory, SimpleDateFormat simpleDateFormat) {
        input.put("test", "test");
    }
}
