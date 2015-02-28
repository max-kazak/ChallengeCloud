package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.UserSettingDao;
import com.codegroup.challengecloud.model.UserSetting;
import org.springframework.stereotype.Repository;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Yefim on 23.02.2015.
 */
@Repository("userSettingDao")
public class UserSettingDaoMySQL extends HibernateDao implements UserSettingDao {

    private static final Logger log = Logger.getLogger(UserSettingDaoMySQL.class);

    @Override
    public void save(UserSetting userSetting) {
        log.debug("save userSetting " + userSetting.getId());
        getSession().save(userSetting);
    }

    @Override
    public void update(UserSetting userSetting) {
        log.debug("update userSetting " + userSetting.getId());
        getSession().update(userSetting);
    }

    @Override
    public void delete(UserSetting userSetting) {
        log.debug("delete userSetting " + userSetting.getId());
        getSession().delete(userSetting);
    }

    @Override
    public UserSetting findById(String settingsId) {
        log.debug("find by ID userSetting " + settingsId);
        List settingsList = find("from UserSetting where USER_ID = ?", settingsId);
        return (UserSetting)settingsList.get(0);
    }
}
