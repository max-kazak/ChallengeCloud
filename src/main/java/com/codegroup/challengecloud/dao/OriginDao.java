package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Origin;

/**
 * Created by Yefim on 25.02.2015.
 */
public interface OriginDao {
    public void save(Origin origin);
    public void update(Origin origin);
    public void delete(Origin origin);

    public Origin findById(String originId);
}
