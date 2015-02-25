package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.model.UserSettings;

/**
 * Created by Yefim on 23.02.2015.
 */
public interface UserSettingsDao {

    public void save(UserSettings userSettings);
    public void update(UserSettings userSettings);
    public void delete(UserSettings userSettings);

    public UserSettings findById(String settingsId);
}
