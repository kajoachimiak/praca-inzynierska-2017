package com.pracainzynierska.model.dao;

import com.pracainzynierska.model.entities.Template;

/**
 * Created by karol on 09.12.17.
 */
public interface TemplateDAO {
    Template getById(Integer templateId);
    Template getByIdAndName(Integer templateId, String name);
}
