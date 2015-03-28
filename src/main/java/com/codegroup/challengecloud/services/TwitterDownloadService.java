package com.codegroup.challengecloud.services;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Yefim on 19.03.2015.
 */
@Service("twitterDownloadService")
public class TwitterDownloadService {
    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Inject
    public TwitterDownloadService(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    public List<Tweet> downloadPosts() {
        List<Tweet> posts = twitter.searchOperations().search("#ChallengeCloud", 2).getTweets();
        return posts;
    }
}
