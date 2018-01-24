package com.pracainzynierska.model.dao.impl;

import com.pracainzynierska.model.dao.UczestnikDAO;
import com.pracainzynierska.model.entities.Uczestnik;
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
public class UczestnikDAOImpl implements UczestnikDAO {
    private static final Logger LOG = Logger.getLogger(UczestnikDAOImpl.class);
    @PersistenceContext
    private EntityManager em;

    public UczestnikDAOImpl() {
    }

    @Override
    public Uczestnik findByLogin(String login) {
        List<Uczestnik> users = em.createNamedQuery(Uczestnik.FIND_BY_LOGIN, Uczestnik.class)
                .setParameter("login", login)
                .getResultList();

        return users.size() == 1 ? users.get(0) : null;
    }
}
