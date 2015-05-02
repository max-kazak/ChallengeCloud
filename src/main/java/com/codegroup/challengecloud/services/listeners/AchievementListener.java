package com.codegroup.challengecloud.services.listeners;

import com.codegroup.challengecloud.services.HistoryService;
import com.codegroup.challengecloud.services.events.AchievementEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by Nipel-Crumple on 02.05.2015.
 */
@Service("achievementListener")
public class AchievementListener implements ApplicationListener<AchievementEvent> {

    @Autowired
    HistoryService historyService;
    @Override
    public void onApplicationEvent(AchievementEvent event) {
        historyService.createHistory(event.getUser().getId(), new Timestamp(event.getTime()),
                event.getId(), event.getBadge().getId());
    }
}
