package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.OriginDao;
import com.codegroup.challengecloud.model.Origin;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Yefim on 25.02.2015.
 */
public class OriginDaoMySQL extends HibernateDao implements OriginDao {

    private static final Logger log = Logger.getLogger(OriginDaoMySQL.class);

    @Override
    public void save(Origin origin) {
        log.debug("save origin " + origin.getId());
        getSession().save(origin);
    }

    @Override
    public void update(Origin origin) {
        log.debug("update origin " + origin.getId());
        getSession().update(origin);
    }

    @Override
    public void delete(Origin origin) {
        log.debug("delete origin " + origin.getId());
        getSession().delete(origin);
    }

    @Override
    public Origin findById(String originId) {
        log.debug("find by Id origin with ID: " + originId);
        List originList = find("from Origin where ORIGIN_ID = ?", originId);
        return (Origin)originList.get(0);
    }
}
