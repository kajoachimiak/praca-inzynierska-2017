package com.pracainzynierska.model.dao.impl;

import com.pracainzynierska.model.dao.TemplateDAO;
import com.pracainzynierska.model.entities.Template;
import com.pracainzynierska.model.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by karol on 09.12.17.
 */
@Transactional
@Repository
public class TemplateDAOImpl implements TemplateDAO {
    private static final Logger LOG = Logger.getLogger(TemplateDAOImpl.class);
    @PersistenceContext
    private EntityManager em;

    public TemplateDAOImpl(){
    }

    @Override
    public Template getById(Integer templateId) {
        List<Template> templates = em.createNamedQuery(Template.FIND_BY_ID, Template.class)
                .setParameter("id", templateId)
                .getResultList();
        return templates.size() == 1 ? templates.get(0) : null;
    }

    @Override
    public Template getByIdAndName(Integer templateId, String name) {
        List<Template> templates = em.createNamedQuery(Template.FIND_BY_ID_AND_NAME, Template.class)
                .setParameter("id", templateId).setParameter("name",name)
                .getResultList();
        return templates.size() == 1 ? templates.get(0) : null;
    }
}
