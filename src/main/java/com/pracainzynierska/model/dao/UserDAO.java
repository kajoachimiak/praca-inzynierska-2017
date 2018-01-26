package com.pracainzynierska.model.dao;

import com.pracainzynierska.model.entities.User;

/**
 * Created by karol on 14.01.18.
 */
public interface UserDAO {
    User findByLogin(String login);
}
