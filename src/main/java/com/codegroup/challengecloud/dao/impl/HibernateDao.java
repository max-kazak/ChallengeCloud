package com.codegroup.challengecloud.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * Created by Max on 24.01.2015.
 */
public class HibernateDao{

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    protected List<Objects> find(String query, String parameter){
        return getSession().createQuery(query).setParameter(0, parameter).list();
    }
}
