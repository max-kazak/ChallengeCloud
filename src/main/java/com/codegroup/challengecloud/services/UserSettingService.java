package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.UserSettingDao;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.model.UserSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yefim on 04.03.2015.
 */
@Service("userSettingService")
public class UserSettingService {
    @Autowired
    UserSettingDao userSettingDao;

    public void setUserSettingDao(UserSettingDao userSettingDao) {
        this.userSettingDao = userSettingDao;
    }

    public void updateUserSetting(UserSetting userSetting) {
        userSettingDao.update(userSetting);
    }

    public void deleteUserSetting(UserSetting userSetting) {
        userSettingDao.delete(userSetting);
    }

    @Transactional
    public UserSetting createSetting(String id, String lang, User user){
        UserSetting userSetting = new UserSetting();
        userSetting.setId(id);
        userSetting.setLang(lang);
        userSetting.setUser(user);

        userSettingDao.save(userSetting);

        return userSetting;
    }

    @Transactional
    public UserSetting findById(String id){
        return userSettingDao.findById(id);
    }
}
