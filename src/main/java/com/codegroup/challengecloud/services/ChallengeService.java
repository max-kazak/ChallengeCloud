package com.codegroup.challengecloud.services;

import java.util.List;

import com.codegroup.challengecloud.dao.ChallengeDao;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.ChallengeGroup;
import com.codegroup.challengecloud.model.Image;
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
    
    @Autowired
    ImageService imageService;

    public void setChallengeDao(ChallengeDao challengeDao) {
        this.challengeDao = challengeDao;
    }

    public void updateProfile(Challenge challenge) {
    	challengeDao.update(challenge);
    }
    
    /**
     * No image and no challenge group constructor for challenge
     */
    @Transactional
    public Challenge createChallenge(String title, String description, Integer difficulty, String hashtag){
    	Challenge challenge = new Challenge();
    	challenge.setId(Generator.generateId());
    	challenge.setTitle(title);
    	challenge.setDescription(description);
    	challenge.setDifficulty(difficulty);
    	challenge.setHashtag(hashtag);
    	challenge.setChallengeGroup(challengeGroupService.findById("5d2c117e674b39de"));
    	challenge.setImage(imageService.getImage("123123"));
    	
    	challengeDao.save(challenge);

        return challenge;
    }
    
    @Transactional
    public Challenge createChallenge(String title, String description, Integer difficulty,
    		String hashtag, Image image){
    	Challenge challenge = new Challenge();
    	challenge.setId(Generator.generateId());
    	challenge.setTitle(title);
    	challenge.setDescription(description);
    	challenge.setDifficulty(difficulty);
    	challenge.setHashtag(hashtag);
    	challenge.setChallengeGroup(challengeGroupService.findById("5d2c117e674b39de"));
    	challenge.setImage(image);
    	
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
    		return challengeDao.findAll();
    }
}
