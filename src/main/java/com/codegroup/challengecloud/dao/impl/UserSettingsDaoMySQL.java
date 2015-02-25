package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.UserSettingsDao;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.model.UserSettings;
import org.springframework.stereotype.Repository;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Yefim on 23.02.2015.
 */
@Repository("userSettingsDao")
public class UserSettingsDaoMySQL extends HibernateDao implements UserSettingsDao {

    private static final Logger log = Logger.getLogger(UserSettingsDaoMySQL.class);

    @Override
    public void save(UserSettings userSettings) {
        log.debug("save userSettings " + userSettings.getId());
        getSession().save(userSettings);
    }

    @Override
    public void update(UserSettings userSettings) {
        log.debug("update userSettings " + userSettings.getId());
        getSession().update(userSettings);
    }

    @Override
    public void delete(UserSettings userSettings) {
        log.debug("delete userSettings " + userSettings.getId());
        getSession().delete(userSettings);
    }

    @Override
    public UserSettings findById(String settingsId) {
        log.debug("find by ID userSettings " + settingsId);
        List settingsList = find("from Usersettings where USER_ID = ?", settingsId);
        return (UserSettings)settingsList.get(0);
    }
}
