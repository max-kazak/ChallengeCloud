package com.codegroup.challengecloud.services.social;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.UserService;

@Service
public class ConnectionSignUpAdapter implements ConnectionSignUp{

	private static final Logger log = Logger.getLogger(ConnectionSignUpAdapter.class);
	
	@Autowired
	UserService userService;
	
	@Override
	public String execute(Connection<?> connection) {
		
		UserProfile userProfile = connection.fetchUserProfile();
		
		if(userProfile == null) {
			log.error("can't fetch user profile from connection");
			return null;
		}
		
		User user = userService.createProfile(	userProfile.getUsername(), 
									userProfile.getEmail(), 
									null, 
									userProfile.getName());		
		
		return user.getId();
	}

}
