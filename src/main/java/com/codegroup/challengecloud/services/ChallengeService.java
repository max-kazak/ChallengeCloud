package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.ChallengeDao;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.utils.Generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vladimir Zhdanov on 28.02.2015.
 */

@Service("challengeService")
public class ChallengeService {

    @Autowired
    ChallengeDao challengeDao;

    public void setChallengeDao(ChallengeDao challengeDao) {
        this.challengeDao = challengeDao;
    }

    public void updateProfile(Challenge challenge) {
    	challengeDao.update(challenge);
    }

    @Transactional
    public Challenge createChallenge(String title, String description, Integer difficulty, String hashtag){
    	Challenge challenge = new Challenge();
    	challenge.setId(Generator.generateId());
    	challenge.setTitle(title);
    	challenge.setDescription(description);
    	challenge.setDifficulty(difficulty);
    	challenge.setHashtag(hashtag);
    	//challenge.setChallengeGroup(challengeGroup);

    	challengeDao.save(challenge);

        return challenge;
    }

    //TODO: When DAO will be ready, uncomment
//    @Transactional
//    public Challenge findByTitle(String title) {
//        return challengeDao.findByTitle(title);
//    }
    
    @Transactional
    public Challenge findById(String id) {
    	return challengeDao.findById(id);
    }
}
