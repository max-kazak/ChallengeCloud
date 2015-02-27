package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.ChallengeDao;
import com.codegroup.challengecloud.model.Challenge;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created by Vladimir Zhdanov on 27.02.2015.
 */
@Repository("challengeDao")
public class ChallengeDaoMySQL extends HibernateDao implements ChallengeDao {

	private static final Logger log = Logger.getLogger(ChallengeDaoMySQL.class);
	
    @Override
    public void save(Challenge challenge) {
    	log.debug("saving challenge " + challenge.getId());
        getSession().save(challenge);
    }

    @Override
    public void update(Challenge challenge) {
    	log.debug("updating challenge " + challenge.getId());
        getSession().update(challenge);
    }

    @Override
    public void delete(Challenge challenge) {
    	log.debug("removing challenge " + challenge.getId());
        getSession().delete(challenge);
    }

    @Override
    public Challenge findById(String id) {
    	log.debug("looking for challenge by id = " + id);
        List list = find("from Challenge where challenge_id = ?", id);
        return (Challenge) list.get(0);
    }


}
