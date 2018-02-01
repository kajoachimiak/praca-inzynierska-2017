package com.pracainzynierska.security;

/**
 * Created by karol on 18.01.18.
 */

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final String AJAX_HEADER_TRUE = "true";
    private AuthenticationSuccessHandler defaultHandler;

    public CustomAuthenticationSuccessHandler(AuthenticationSuccessHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if (AJAX_HEADER_TRUE.equals(request.getHeader("X-Login-Ajax-call"))) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            defaultHandler.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
