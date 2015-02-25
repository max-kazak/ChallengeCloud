package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.OriginsDao;
import com.codegroup.challengecloud.model.Origins;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Yefim on 25.02.2015.
 */
public class OriginsDaoMySQL extends HibernateDao implements OriginsDao {

    private static final Logger log = Logger.getLogger(OriginsDaoMySQL.class);

    @Override
    public void save(Origins origin) {
        log.debug("save origin " + origin.getId());
        getSession().save(origin);
    }

    @Override
    public void update(Origins origin) {
        log.debug("update origin " + origin.getId());
        getSession().update(origin);
    }

    @Override
    public void delete(Origins origin) {
        log.debug("delete origin " + origin.getId());
        getSession().delete(origin);
    }

    @Override
    public Origins findById(String originId) {
        log.debug("find by Id origin with ID: " + originId);
        List originList = find("from Origins where ORIGIN_ID = ?", originId);
        return (Origins)originList.get(0);
    }
}
