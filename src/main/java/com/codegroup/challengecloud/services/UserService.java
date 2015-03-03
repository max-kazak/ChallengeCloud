package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.constants.UserRoles;
import com.codegroup.challengecloud.dao.UserDao;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.utils.Generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Max on 24.01.2015.
 */

@Service("userService")
public class UserService {

    @Autowired
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updateProfile(User user) {
        userDao.update(user);
    }

    public void deleteProfile(User user) {
        userDao.delete(user);
    }

    @Transactional
    public User createProfile(String login, String email, String pass, String name) {
        User user = new User();
        user.setId(Generator.generateId());
        user.setLogin(login);
        user.setPass(pass);
        user.setEmail(email);
        user.setName(name);
        user.setRole(UserRoles.ROLE_USER_ID);

        userDao.save(user);

        return user;
    }

    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Transactional
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Transactional
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Transactional
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        /*Spring User has our User Id as Name (see in  ChallengerUserDetailsService.buildUserForAuthentication) */
        return findById(authentication.getName());
    }
}
