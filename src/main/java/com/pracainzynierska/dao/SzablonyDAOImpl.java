package com.pracainzynierska.dao;

import com.pracainzynierska.model.Szablony;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by karol on 09.12.17.
 */
public class SzablonyDAOImpl implements SzablonyDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Szablony getById() {
        Session session = this.sessionFactory.openSession();
//        return session.getNamedQuery("Szablony.getById");
        return null;
    }
}
