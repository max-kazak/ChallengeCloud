package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.ChallengeGroup;

/**
 * Created by Vladimir Zhdanov on 27.02.2015.
 */
public interface ChallengeGroupDao {

    public void save(ChallengeGroup challengeGroup);
    public void update(ChallengeGroup challengeGroup);
    public void delete(ChallengeGroup challengeGroup);

    public ChallengeGroup findById(String id);
    //TODO: implement this
    //public ChallengeGroup findByTitle(String title);
    //TODO: implement this
    //public ChallengeGroup findByDescription(String description);

}
