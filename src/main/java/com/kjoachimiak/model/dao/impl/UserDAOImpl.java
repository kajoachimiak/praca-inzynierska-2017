package com.kjoachimiak.model.dao.impl;

import com.kjoachimiak.model.dao.UserDAO;
import com.kjoachimiak.model.entities.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by karol on 14.01.18.
 */
@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);
    @PersistenceContext
    private EntityManager em;

    public UserDAOImpl() {
    }

    @Override
    public User findByLogin(String login) {
        List<User> users = em.createNamedQuery(User.FIND_BY_LOGIN, User.class)
                .setParameter("login", login)
                .getResultList();

        return users.size() == 1 ? users.get(0) : null;
    }
}
