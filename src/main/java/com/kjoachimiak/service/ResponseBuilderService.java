package com.kjoachimiak.service;

import com.kjoachimiak.dto.EventHistoryResponseDTO;
import com.kjoachimiak.model.entities.EventHistory;
import com.kjoachimiak.model.entities.Template;
import com.kjoachimiak.model.entities.User;

import java.util.List;

/**
 * Created by karol on 27.01.18.
 */
public interface ResponseBuilderService {
    String buildUserRelationsResponse(User user);
    String buildTemplateListResponse(String ownerName, List<Template> templateList);
    String buildFileResponse(String fileContent, Boolean status);
    String buildUrlResponse(String url);
    EventHistoryResponseDTO buildEventHistoryResponse(Template template, List<EventHistory> eventHistories);
}
