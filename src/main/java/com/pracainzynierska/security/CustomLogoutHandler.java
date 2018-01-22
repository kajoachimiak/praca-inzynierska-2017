package com.pracainzynierska.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by karol on 22.01.18.
 */
public class CustomLogoutHandler implements LogoutSuccessHandler {
    private static final String AJAX_HEADER_TRUE = "true";
    private SimpleUrlLogoutSuccessHandler defaultHandler;

    public CustomLogoutHandler(SimpleUrlLogoutSuccessHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (AJAX_HEADER_TRUE.equals(request.getHeader("X-Login-Ajax-call"))) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            defaultHandler.onLogoutSuccess(request, response, authentication);
        }
    }
}
