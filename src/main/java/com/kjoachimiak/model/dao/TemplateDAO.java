package com.kjoachimiak.model.dao;

import com.kjoachimiak.model.entities.Template;

/**
 * Created by karol on 09.12.17.
 */
public interface TemplateDAO {
    Template getById(Integer templateId);
    Template getByIdAndName(Integer templateId, String name);
}
