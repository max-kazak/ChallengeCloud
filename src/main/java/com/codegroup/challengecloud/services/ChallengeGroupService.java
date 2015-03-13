package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.ChallengeGroupDao;
import com.codegroup.challengecloud.model.ChallengeGroup;
import com.codegroup.challengecloud.model.Image;
import com.codegroup.challengecloud.utils.Generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vladimir Zhdanov on 01.03.2015.
 */

@Service("challengeGroupService")
public class ChallengeGroupService {

    @Autowired
    ChallengeGroupDao challengeGroupDao;
    @Autowired
    ImageService imageService;

    public void setChallengeGroupDao(ChallengeGroupDao challengeGroupDao) {
        this.challengeGroupDao = challengeGroupDao;
    }

    public void updateProfile(ChallengeGroup challengeGroup) {
    	challengeGroupDao.update(challengeGroup);
    }

    @Transactional
    public ChallengeGroup createChallengeGroup(String name, byte[] imageData){
    	ChallengeGroup challengeGroup = new ChallengeGroup();
    	challengeGroup.setId(Generator.generateId());
    	challengeGroup.setName(name);

        Image image = imageService.createImage(name, 'M', imageData);
    	challengeGroup.setImageId(image.getId());

    	challengeGroupDao.save(challengeGroup);

        return challengeGroup;
    }

    //TODO: When DAO will be ready, uncomment
//    @Transactional
//    public ChallengeGroup findByName(String name) {
//        return challengeGroupDao.findByName(name);
//    }
    
    @Transactional
    public ChallengeGroup findById(String id) {
    	return challengeGroupDao.findById(id);
    }
    
    @Transactional
    public List<ChallengeGroup> findAll() {
    	return challengeGroupDao.findAll();
    }
}
