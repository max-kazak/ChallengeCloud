package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.OriginDao;
import com.codegroup.challengecloud.model.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yefim on 04.03.2015.
 */
@Service("originService")
public class OriginService {
    @Autowired
    OriginDao originDao;

    public void setOriginDao(OriginDao originDao) {
        this.originDao = originDao;
    }

    public void updateOrigin(Origin origin) {
        originDao.update(origin);
    }

    public void deleteOrigin(Origin origin) {
        originDao.delete(origin);
    }


    @Transactional
    public Origin createOrigin(String id, String name) {
        Origin origin = new Origin();
        origin.setId(id);
        origin.setName(name);

        originDao.save(origin);

        return origin;
    }

    @Transactional
    public Origin findById(String id) {
        return originDao.findById(id);
    }
}
