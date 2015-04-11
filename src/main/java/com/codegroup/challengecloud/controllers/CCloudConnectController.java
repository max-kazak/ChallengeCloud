package com.codegroup.challengecloud.controllers;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Nipel-Crumple on 04.04.2015.
 */
public class CCloudConnectController extends ConnectController{

    @Inject
    public CCloudConnectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    @Override
    protected RedirectView connectionStatusRedirect(String providerId, NativeWebRequest request) {
        String path = "/settings";
        return new RedirectView(path, true);
    }

    @Override
    protected String connectView(String providerId) {
        return "/signinup";
    }

    @Override
    protected String connectedView(String providerId) {
        return "/signinup";
    }

}
