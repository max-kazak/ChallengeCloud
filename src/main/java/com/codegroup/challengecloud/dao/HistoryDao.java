package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.History;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Created by Krokhin on 19.04.2015.
 */
@Repository("historyDao")
public interface HistoryDao {
    public void save(History history);

    public void update(History history);

    public void delete(History history);

    public History findByRefId(String refId);

    public History findById(String historyId);
}
