package com.codegroup.challenger.dao.impl;

import com.codegroup.challenger.dao.UserDao;
import com.codegroup.challenger.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Max on 24.01.2015.
 */
@Repository("userDao")
public class UserDaoMySQL extends HibernateDao implements UserDao {

    @Override
    public void save(User user) {
        getSession().save(user);
    }

    @Override
    public void update(User user) {
        getSession().update(user);
    }

    @Override
    public void delete(User user) {
        getSession().delete(user);
    }

    @Override
    public User findById(String id) {
        List list = find("from User where user_id = ?", id);
        return (User) list.get(0);
    }

    @Override
    public User findByLogin(String login) {
        List list = find("from User where login = ?", login);
        return (User) list.get(0);
    }

    @Override
    public User findByEmail(String email) {
        List list = find("from User where email = ?", email);
        return (User) list.get(0);
    }
}
