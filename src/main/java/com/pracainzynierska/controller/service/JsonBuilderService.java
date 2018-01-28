package com.pracainzynierska.controller.service;

import com.pracainzynierska.model.entities.Template;
import com.pracainzynierska.model.entities.User;

import java.util.List;

/**
 * Created by karol on 27.01.18.
 */
public interface JsonBuilderService {
    String buildUserRelationsResponse(User user);
    String buildTemplateListResponse(String ownerName, List<Template> templateList);
    String buildFileResponse(String fileContent);
    String buildUrlResponse(String url);
}
