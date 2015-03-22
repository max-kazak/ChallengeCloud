package com.codegroup.challengecloud.services;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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

    public SearchResults downloadPosts() {
    	SearchResults results = twitter.searchOperations().search("#spring");
        return results;
    }
}
