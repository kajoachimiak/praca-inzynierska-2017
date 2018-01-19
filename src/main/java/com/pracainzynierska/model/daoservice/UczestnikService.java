package com.pracainzynierska.model.daoservice;

import com.pracainzynierska.model.dao.UczestnikDAO;
import com.pracainzynierska.model.entities.Uczestnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by karol on 14.01.18.
 */
@Service("uczestnikService")
public class UczestnikService {
    @Autowired
    private UczestnikDAO uczestnikDAO;

    public UczestnikService() {
    }

    public Uczestnik getUserByLogin(final String login) throws UsernameNotFoundException {
        return uczestnikDAO.findByLogin(login);

    }
}
