package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.ChallengeGroupDao;
import com.codegroup.challengecloud.model.ChallengeGroup;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vladimir Zhdanov on 27.02.2015.
 */
@Repository("challengeGroupDao")
public class ChallengeGroupDaoMySQL extends HibernateDao implements ChallengeGroupDao {

	private static final Logger log = Logger.getLogger(ChallengeGroupDaoMySQL.class);
	
    @Override
    public void save(ChallengeGroup challengeGroup) {
    	log.debug("saving challengeGroup " + challengeGroup.getId());
        getSession().save(challengeGroup);
    }

    @Override
    public void update(ChallengeGroup challengeGroup) {
    	log.debug("updating challengeGroup " + challengeGroup.getId());
        getSession().update(challengeGroup);
    }

    @Override
    public void delete(ChallengeGroup challengeGroup) {
    	log.debug("removing challengeGroup " + challengeGroup.getId());
        getSession().delete(challengeGroup);
    }

    @Override
    public ChallengeGroup findById(String id) {
    	log.debug("looking for challengeGroup by id = " + id);
        List list = find("from ChallengeGroup where group_id = ?", id);
        return (ChallengeGroup) list.get(0);
    }


}
