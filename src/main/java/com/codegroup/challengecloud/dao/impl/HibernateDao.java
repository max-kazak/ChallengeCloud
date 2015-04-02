package com.codegroup.challengecloud.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * Created by Max on 24.01.2015.
 */
public class HibernateDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /* Created on 01.03.2015 by Vladimir Zhdanov */

    /**
     * Querry without parameters. Can be used to get all elements,
     * like challengeGroups.
     *
     * @param query - Querry string
     * @return List of elements
     * @author Vladimir Zhdanov
     */
    protected List<Objects> find(String query) {
        return getSession().createQuery(query).list();
    }

    protected List<Objects> find(String query, String parameter) {
        return getSession().createQuery(query).setParameter(0, parameter).list();
    }

    /**
     * Find smth ib DB using List as parameter
     * example of usage: find("from Post where subscription_id in (:param)", parameters)
     *@author Yefim on 31.03.2015
     */
    protected List<Objects> find(String query, List parameterList) {
        return getSession().createQuery(query).setParameterList("param", parameterList).list();
    }
}
