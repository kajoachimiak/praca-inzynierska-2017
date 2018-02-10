package com.kjoachimiak.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by karol on 22.01.18.
 */
public class CustomAuthenticationFailuerHandler implements AuthenticationFailureHandler {
    private static final Logger LOG = Logger.getLogger(CustomAuthenticationFailuerHandler.class);
    private static final String AJAX_HEADER_TRUE = "true";
    private AuthenticationFailureHandler defaultHandler;

    public CustomAuthenticationFailuerHandler(AuthenticationFailureHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        if (AJAX_HEADER_TRUE.equals(request.getHeader("X-Login-Ajax-call"))) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            LOG.debug("Custom authentication mode");
        }
        else {
            defaultHandler.onAuthenticationFailure(request, response,e);
            LOG.debug("Default authentication mode");
        }
        LOG.info("User authentication failed!",e);
    }
}
