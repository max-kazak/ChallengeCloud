package com.codegroup.challengecloud.dao;

import com.codegroup.challengecloud.model.Challenge;

/**
 * Created by Vladimir Zhdanov on 27.02.2015.
 */
public interface ChallengeDao {

    public void save(Challenge challenge);
    public void update(Challenge challenge);
    public void delete(Challenge challenge);

    public Challenge findById(String id);
    //TODO: implement this
    //public Challenge findByTitle(String title);
    //TODO: implement this
    //public Challenge findByDescription(String description);

}