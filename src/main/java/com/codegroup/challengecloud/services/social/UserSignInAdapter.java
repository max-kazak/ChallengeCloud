package com.codegroup.challengecloud.services.social;

import javax.inject.Inject;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import com.codegroup.challengecloud.services.security.ChallengerUserDetailsService;
import org.apache.log4j.Logger;

@Service
public class UserSignInAdapter implements SignInAdapter{

	private static Logger logger = Logger.getLogger(UserSignInAdapter.class);

	@Inject
	ChallengerUserDetailsService userDetailsService;
	
	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {

		UserDetails userDetail = userDetailsService.loadUserById(userId);
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		logger.debug("SUCCESS in SIGN IN\n");
		return "/home";
	}

}