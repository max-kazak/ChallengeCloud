package com.codegroup.challengecloud.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.codegroup.challengecloud.services.social.ConnectionSignUpAdapter;
import com.codegroup.challengecloud.services.social.UserSignInAdapter;

/**
 * Spring Social Configuration.
 */

@Configuration
@EnableSocial
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class SocialConfig implements SocialConfigurer{

	@Autowired
	private DataSource dataSource;
	

	@Override
	public void addConnectionFactories(	ConnectionFactoryConfigurer cfConfig, Environment environment) {
		cfConfig.addConnectionFactory(new TwitterConnectionFactory(
				environment.getProperty("twitter.appKey"),
		        environment.getProperty("twitter.appSecret")));		
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new UserIdSource() {
			@Override
			public String getUserId() {
				return ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
			}
		};
	}

	@Autowired
	ConnectionSignUpAdapter connectionSignUpAdapter;
	
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(
				dataSource, 
				connectionFactoryLocator, 
				Encryptors.noOpText());
	    repository.setConnectionSignUp(connectionSignUpAdapter);
		return repository;
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public Twitter twitter(ConnectionRepository repository) {
		Connection<Twitter> connection = repository.findPrimaryConnection(Twitter.class);
		return connection != null ? connection.getApi() : null;
	}

	@Bean
	public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, 
			UsersConnectionRepository usersConnectionRepository,
			UserSignInAdapter userSignInAdapter) {
		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, userSignInAdapter);
	}
	
	@Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }

	
}
