package com.pracainzynierska.model.dao.impl;

import com.pracainzynierska.model.dao.SzablonyDAO;
import com.pracainzynierska.model.entities.Szablony;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by karol on 09.12.17.
 */
public class SzablonyDAOImpl implements SzablonyDAO {
    private static final Logger LOG = Logger.getLogger(SzablonyDAOImpl.class);
    private SessionFactory sessionFactory;

    public SzablonyDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Szablony getById(Integer szablonyId) {
        Szablony szablony = new Szablony();
        try {
            Session session = this.sessionFactory.openSession();
            session.beginTransaction();
            szablony = (Szablony) session.get(Szablony.class, szablonyId);
        }catch (Exception e){
            LOG.error("Error accessing database",e);
        }
        return szablony;
    }
}
