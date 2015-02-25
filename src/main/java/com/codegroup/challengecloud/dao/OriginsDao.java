package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Origins;

/**
 * Created by Yefim on 25.02.2015.
 */
public interface OriginsDao {
    public void save(Origins origin);
    public void update(Origins origin);
    public void delete(Origins origin);

    public Origins findById(String originId);
}
