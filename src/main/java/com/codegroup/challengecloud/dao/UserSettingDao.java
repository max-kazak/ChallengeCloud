package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.UserSetting;

/**
 * Created by Yefim on 23.02.2015.
 */
public interface UserSettingDao {

    public void save(UserSetting userSetting);
    public void update(UserSetting userSetting);
    public void delete(UserSetting userSetting);

    public UserSetting findById(String settingsId);
}
