package com.pracainzynierska.controller.service.impl;

import com.pracainzynierska.model.dao.TemplateDAO;
import com.pracainzynierska.model.entities.Template;
import com.pracainzynierska.model.entities.User;
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

    public Template getTemplateById(Integer templateId) {
        return templateDAO.getById(templateId);
    }

    public Template getTemplateByIdAndName(Integer templateId, String templateName) {
        return templateDAO.getByIdAndName(templateId, templateName);
    }

    public boolean isUserAuthorizedToUseTemplate(User user, Template template) throws NullPointerException {
        boolean result = false;
        Integer groupId = user.getGroup().getId();
        Integer editionId = user.getGroup().getEdition().getId();
        Integer courseId = user.getGroup().getEdition().getCourse().getId();
        if (template.getUser() != null) {
            result = user.getId().equals(template.getUser().getId());
        }
        if (template.getGroup() != null) {
            result = groupId.equals(template.getGroup().getId());
        }
        if (template.getEdition() != null) {
            result = editionId.equals(template.getEdition().getId());
        }
        if (template.getCourse() != null) {
            result = courseId.equals(template.getCourse().getId());
        }
        return result;
    }
}
