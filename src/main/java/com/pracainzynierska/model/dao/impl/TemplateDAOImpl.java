package com.pracainzynierska.model.dao.impl;

import com.pracainzynierska.model.dao.TemplateDAO;
import com.pracainzynierska.model.entities.Template;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by karol on 09.12.17.
 */
public class TemplateDAOImpl implements TemplateDAO {
    private static final Logger LOG = Logger.getLogger(TemplateDAOImpl.class);
    private SessionFactory sessionFactory;

    public TemplateDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Template getById(Integer templateId) {
        Template template = new Template();
        try {
            Session session = this.sessionFactory.openSession();
            session.beginTransaction();
            template = (Template) session.get(Template.class, templateId);
        }catch (Exception e){
            LOG.error("Error accessing database",e);
        }
        return template;
    }
}
