package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Krokhin on 21.04.2015.
 */
@Service("historyService")
public class HistoryService {
    @Autowired
    HistoryDao historyDao;

    public void setHistoryDao(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }
}
