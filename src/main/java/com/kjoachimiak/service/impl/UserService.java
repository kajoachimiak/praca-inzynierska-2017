package com.kjoachimiak.service.impl;

import com.kjoachimiak.exceptions.ForbiddenException;
import com.kjoachimiak.model.dao.UserDAO;
import com.kjoachimiak.model.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * Created by karol on 14.01.18.
 */
@Service("userService")
public class UserService {
    private static final Logger LOG = Logger.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    public UserService() {
    }

    public User getUserByLogin(final String login) throws UsernameNotFoundException {
        return userDAO.findByLogin(login);
    }

    public String getCurrentUserUsername(Principal principal){
        String username;
        try {
            username = principal.getName();
        }catch (NullPointerException e){
            LOG.error("Requested principal does not exist. User not authorized.", e);
            throw new ForbiddenException();
        }
        return username;
    }
}
