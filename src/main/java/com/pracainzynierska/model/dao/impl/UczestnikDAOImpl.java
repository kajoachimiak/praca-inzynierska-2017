package com.pracainzynierska.model.dao.impl;

import com.pracainzynierska.model.dao.UczestnikDAO;
import com.pracainzynierska.model.entities.Szablony;
import com.pracainzynierska.model.entities.Uczestnik;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karol on 14.01.18.
 */
@Repository
public class UczestnikDAOImpl implements UczestnikDAO {
    private static final Logger LOG = Logger.getLogger(UczestnikDAOImpl.class);
    private SessionFactory sessionFactory;

    public UczestnikDAOImpl() {
    }

    public UczestnikDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Uczestnik findByLogin(String login) {
        List <Uczestnik> uczestnikList = new ArrayList<>();
        try {
            Session session = this.sessionFactory.openSession();
            uczestnikList = session.createQuery("from Uczestnik where login=?")
                    .setParameter(0, login)
                    .list();
            session.close();
        } catch (Exception e) {
            LOG.error("Error accessing database", e);
        }
        return uczestnikList.size() > 0 ? uczestnikList.get(0) : null;
    }
}
