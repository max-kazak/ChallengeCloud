package com.codegroup.challengecloud.services;

import java.util.List;

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
    
    @Autowired
    ChallengeGroupService challengeGroupService;
    
    private static List<Challenge> challengesAll = null;

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
    	challenge.setChallengeGroup(challengeGroupService.findById("5d2c117e674b39de"));
    	challenge.setImageId("123123");
    	
    	challengeDao.save(challenge);

        return challenge;
    }


    @Transactional
    public Challenge findByTitle(String title) {
        return challengeDao.findByTitle(title);
    }
    
    @Transactional
    public Challenge findById(String id) {
    	return challengeDao.findById(id);
    }

    @Transactional
    public List<Challenge> findAll() {
    	if (challengesAll != null)
    		return challengesAll;
    	else
    		return challengesAll = challengeDao.findAll();
    }
}
