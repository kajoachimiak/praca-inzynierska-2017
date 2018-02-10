package com.kjoachimiak.model.dao;

import com.kjoachimiak.model.entities.User;

/**
 * Created by karol on 14.01.18.
 */
public interface UserDAO {
    User findByLogin(String login);
}
