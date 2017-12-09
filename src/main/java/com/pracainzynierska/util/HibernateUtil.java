package com.pracainzynierska.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by karol on 09.12.17.
 */
public class HibernateUtil {
    private static final Logger log = Logger.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            log.info("Hibernate Configuration loaded");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties()).build();
            log.info("Hibernate serviceRegistry created");

            return  config.buildSessionFactory(serviceRegistry);
        }catch (Throwable ex){
            log.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}
