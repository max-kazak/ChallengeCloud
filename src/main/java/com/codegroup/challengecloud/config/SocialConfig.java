package com.codegroup.challengecloud.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import com.codegroup.challengecloud.services.social.ConnectionSignUpAdapter;
import com.codegroup.challengecloud.services.social.UserSignInAdapter;

/**
 * Spring Social Configuration.
 */

@Configuration
@PropertySource("classpath:application.properties")
public class SocialConfig {

	@Inject
	private Environment environment;
	@Inject
	private DataSource dataSource;
	
	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
	    ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
	    registry.addConnectionFactory(new TwitterConnectionFactory(environment.getProperty("twitter.clientId"),
	        environment.getProperty("twitter.clientSecret")));
	    return registry;
	}
	
	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
	    JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, 
	        connectionFactoryLocator(), Encryptors.noOpText());
	    repository.setConnectionSignUp(new ConnectionSignUpAdapter());
	    return repository;
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
	    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    return usersConnectionRepository().createConnectionRepository(user.getUsername());
	}

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)   
	public Twitter twitter() {
	    return connectionRepository().getPrimaryConnection(Twitter.class).getApi();
	}
	
	@Bean
	public ProviderSignInController providerSignInController() {
	    return new ProviderSignInController(connectionFactoryLocator(), usersConnectionRepository(),
	        new UserSignInAdapter());
	}
	
	
}
