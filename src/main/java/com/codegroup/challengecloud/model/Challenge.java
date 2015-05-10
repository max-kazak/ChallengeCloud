package com.codegroup.challengecloud.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Vladimir on 27.02.2015.
 */
@Entity
@Table(name = "challenges", catalog = "challenger")
public class Challenge implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;
    String title;
    String description;
    Integer difficulty;
    String hashtag;
    ChallengeGroup challengeGroup;
    Image image;
    String condition;


    public Challenge() {
    }

    @Id
    @Column(name = "CHALLENGE_ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "TITLE", nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name = "DESCRIPTION", nullable = false)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "DIFFICULTY", nullable = false)
    public Integer getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    @Column(name = "HASHTAG", nullable = false)
    public String getHashtag() {
        return hashtag;
    }
    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
    
    
    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", difficulty=" + difficulty + 
                ", hashtag='" + hashtag + '\'' +
                ", group='" + challengeGroup.getId() + '\'' +
                '}';
    }

    /**
     * This method returns ChallengeGroup, which this Challenge
     * belongs to.
     * @return ChallengeGroup of this Challenge.
     * @author Vladimir Zhdanov
     */
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID", nullable = false)
    public ChallengeGroup getChallengeGroup() {
        return challengeGroup;
    }

    public void setChallengeGroup(ChallengeGroup challengeGroup) {
        this.challengeGroup = challengeGroup;
    }

    /**
     * This method returns Image .
     * @return image ID
     * @author Vladimir Zhdanov
     */
    @ManyToOne
    @JoinColumn(name = "IMAGE_ID", nullable = false)
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
}
