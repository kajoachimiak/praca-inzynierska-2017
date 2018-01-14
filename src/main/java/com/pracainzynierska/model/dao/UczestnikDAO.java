package com.pracainzynierska.model.dao;

import com.pracainzynierska.model.entities.Uczestnik;

/**
 * Created by karol on 14.01.18.
 */
public interface UczestnikDAO {
    Uczestnik findByLogin(String login);
}
