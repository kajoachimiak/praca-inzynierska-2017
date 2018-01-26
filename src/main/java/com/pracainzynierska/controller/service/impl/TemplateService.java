package com.pracainzynierska.controller.service.impl;

import com.pracainzynierska.model.dao.TemplateDAO;
import com.pracainzynierska.model.entities.Template;

/**
 * Created by karol on 11.12.17.
 */
public class TemplateService {
    private TemplateDAO templateDAO;

    public TemplateService() {
    }
    public Template getTemplateById(Integer templateId){
        return templateDAO.getById(templateId);
    }
}
