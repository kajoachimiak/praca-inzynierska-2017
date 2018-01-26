package com.pracainzynierska.controller.service.impl;

import com.pracainzynierska.model.dao.TemplateDAO;
import com.pracainzynierska.model.entities.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by karol on 11.12.17.
 */
@Service
public class TemplateService {
    @Autowired
    private TemplateDAO templateDAO;

    public TemplateService() {
    }
    public Template getTemplateById(Integer templateId){
        return templateDAO.getById(templateId);
    }
    public Template getTemplateByIdAndName(Integer templateId, String templateName){
        return templateDAO.getByIdAndName(templateId,templateName);
    }
}
