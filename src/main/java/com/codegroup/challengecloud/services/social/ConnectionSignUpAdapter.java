package com.codegroup.challengecloud.services.social;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.UserService;

public class ConnectionSignUpAdapter implements ConnectionSignUp{

	@Inject
	UserService userService;
	
	@Override
	public String execute(Connection<?> connection) {
		
		UserProfile userProfile = connection.fetchUserProfile();
		
		User user = userService.createProfile(	userProfile.getUsername(), 
									userProfile.getEmail(), 
									null, 
									userProfile.getName());		
		
		return user.getId();
	}

}
