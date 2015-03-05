package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.ChallengeGroup;

import java.util.List;

/**
 * Created by Vladimir Zhdanov on 27.02.2015.
 */
public interface ChallengeGroupDao {

    public void save(ChallengeGroup challengeGroup);
    public void update(ChallengeGroup challengeGroup);
    public void delete(ChallengeGroup challengeGroup);

    public ChallengeGroup findById(String id);
    public List<ChallengeGroup> findAll();
    //TODO: implement this
    //public ChallengeGroup findByTitle(String title);
    //TODO: implement this
    //public ChallengeGroup findByDescription(String description);

}
