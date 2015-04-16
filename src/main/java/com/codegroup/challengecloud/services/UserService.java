package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.constants.UserRoles;
import com.codegroup.challengecloud.dao.UserDao;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.utils.Generator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Max on 24.01.2015.
 */

@Service("userService")
public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class);

    @Autowired
    UserDao userDao;

    public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void updateProfile(User user) {
        userDao.update(user);
    }

    @Transactional
    public void deleteProfile(User user) {
        userDao.delete(user);
    }

    @Transactional
    public User createProfile(String login, String email, String password, String name) {
        User user = new User();
        user.setId(Generator.generateId());
        user.setLogin(login);
        if (password != null) {
            user.setPassword(password);
        } else {
            user.setPassword(Generator.generateHashedPass(password));
        }
        user.setEmail(email);
        user.setName(name);
        user.setRole(UserRoles.ROLE_USER_ID);

        userDao.save(user);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createProfile(User user) {
        user.setId(Generator.generateId());
        user.setRole(UserRoles.ROLE_USER_ID);
        encodePassword(user);

        log.debug("createProfile user params:\n" +
                user.getId() + "\n" +
                user.getLogin() + "\n" +
                user.getEmail() + "\n" +
                user.getName() + "\n" +
                user.getPassword() + "\n" +
                user.getRole());

        userDao.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public User findByEmail(String email) throws IndexOutOfBoundsException {
        return userDao.findByEmail(email);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Transactional(readOnly = true)
    public User findById(String id) {
        return userDao.findById(id);
    }
    
    @Transactional
    public User getCurrentUser() {
    	log.debug("Trying to get current user id");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        /*Spring User has our User Id as Name (see in  ChallengerUserDetailsService.buildUserForAuthentication) */
        String id = authentication.getName();
        log.debug("returning current user " + id);
        return userDao.findById(id);
    }

    private void encodePassword(User user) {
        String passwordToEncode = user.getPassword();
        user.setPassword(Generator.generateHashedPass(passwordToEncode));
    }
    
    /**
     * Created on 07.04.2015 by Vladimir Zhdanov
     * @return
     */
    @Transactional
    public List<User> findAll() {
    		return userDao.findAll();
    }

}
