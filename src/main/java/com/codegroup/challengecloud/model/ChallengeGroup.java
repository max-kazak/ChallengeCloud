package com.codegroup.challengecloud.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vladimir on 27.02.2015.
 */
@Entity
@Table(name = "challengegroups", catalog = "challenger")
public class ChallengeGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;
    String name;
    String imageId;

    Set<Challenge> challenges = new HashSet<Challenge>(0);


    public ChallengeGroup() {
    }

    @Id
    @Column(name = "GROUP_ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "IMAGE_ID", nullable = false)
    public String getImageId() {
        return imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    
    
    
    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", iconref='" + imageId + '\'' +
                '}';
    }

    /**
     * This method returns Set<Challenge>, which has this ChallengeGroup.
     * 
     * @return Set<Challenge>, heaving this CgallengeGroup.
     * @author Vladimir Zhdanov
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "challengeGroup")
    public Set<Challenge> getChallenges() {
        return challenges;
    }

    /**
     * it is necessary to add setters to every field
     * @author Nipel-Crumple
     */
    public void setChallenges(Set<Challenge> challenges) {
        this.challenges = challenges;
    }

}
