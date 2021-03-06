package com.kjoachimiak.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kjoachimiak.helpers.jsonObjects.templateList.TemplateJson;
import com.kjoachimiak.helpers.jsonObjects.templateList.TemplateJsonRoot;
import com.kjoachimiak.helpers.jsonObjects.treeData.*;
import com.kjoachimiak.service.ResponseBuilderService;
import com.kjoachimiak.helpers.enums.NodeType;
import com.kjoachimiak.dto.EventHistoryResponseDTO;
import com.kjoachimiak.dto.EventHistoryResponseElementDTO;
import com.kjoachimiak.model.entities.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
@Service
public class ResponseBuilderServiceImpl implements ResponseBuilderService {
    private static final String HISTORY_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public String buildUserRelationsResponse(User user){
        Group group = user.getGroup();
        Edition edition = group.getEdition();
        Course course = edition.getCourse();

        List<CourseJson> cours = new ArrayList<>();
        List<EditionJson> editionJsons = new ArrayList<>();
        List<GroupJson> groupJsons = new ArrayList<>();
        List<UserJson> userJsons = new ArrayList<>();

        userJsons.add(new UserJson(NodeType.USER.toString(), user.getLogin(),new ArrayList<>()));
        groupJsons.add(new GroupJson(NodeType.GROUP.toString(), group.getName(), userJsons));
        editionJsons.add(new EditionJson(NodeType.EDITION.toString(), edition.getName(), groupJsons));
        cours.add(new CourseJson(NodeType.COURSE.toString(), course.getName(), editionJsons));

        TreeData treeData = new TreeData(cours);

        return new Gson().toJson(treeData);
    }
    public String buildTemplateListResponse(String ownerName, List<Template> templateList){
        List<TemplateJson> resultTemplateJsonList = new ArrayList<>();
        templateList.forEach(template -> {
            resultTemplateJsonList.add(new TemplateJson(template.getId(), template.getName(), template.getDescription(), template.getType().getCode()));
        });
        TemplateJsonRoot templateJsonRoot = new TemplateJsonRoot(ownerName, resultTemplateJsonList);
        return new Gson().toJson(templateJsonRoot);
    }

    @Override
    public String buildFileResponse(String fileContent, Boolean status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", status);
        jsonObject.addProperty("fileContent", fileContent);
        return new Gson().toJson(jsonObject);
    }

    @Override
    public String buildUrlResponse(String url) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("url", url);
        return new Gson().toJson(jsonObject);
    }

    @Override
    public EventHistoryResponseDTO buildEventHistoryResponse(Template template, List<EventHistory> eventHistories) {
        EventHistoryResponseDTO eventHistoryResponse = new EventHistoryResponseDTO();
        eventHistoryResponse.setTemplateId(template.getId());
        eventHistoryResponse.setTemplateName(template.getName());
        eventHistoryResponse.setTemplateHistory(new ArrayList<>());


        SimpleDateFormat dateFormat = new SimpleDateFormat(HISTORY_DATE_FORMAT);
        eventHistories.forEach(eventHistory -> {
            String courseName = null != eventHistory.getCourseContext() ? eventHistory.getCourseContext(): "";
            String editionName = null != eventHistory.getEditionContext() ? eventHistory.getEditionContext() : "";
            String groupName = null != eventHistory.getGroupContext() ? eventHistory.getGroupContext() : "";
            String userName = null != eventHistory.getUserContext() ? eventHistory.getUserContext() : "";

            EventHistoryResponseElementDTO eventHistoryResponseElement = new EventHistoryResponseElementDTO();
            eventHistoryResponseElement.setExecutionTime(dateFormat.format(eventHistory.getExecutionTime()));
            eventHistoryResponseElement.setContent(eventHistory.getContent());
            eventHistoryResponseElement.setCourse(courseName);
            eventHistoryResponseElement.setEdition(editionName);
            eventHistoryResponseElement.setGroup(groupName);
            eventHistoryResponseElement.setUser(userName);
            eventHistoryResponse.getTemplateHistory().add(eventHistoryResponseElement);
        });
        return eventHistoryResponse;
    }

}
