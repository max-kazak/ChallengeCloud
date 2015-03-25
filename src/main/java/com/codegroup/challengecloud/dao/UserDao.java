package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.User;

/**
 * Created by Max on 24.01.2015.
 */
public interface UserDao {

    public void save(User user);
    public void update(User user);
    public void delete(User user);

    public User findById(String id);
    public User findByLogin(String login);
    public User findByEmail(String email) throws IndexOutOfBoundsException;

}
