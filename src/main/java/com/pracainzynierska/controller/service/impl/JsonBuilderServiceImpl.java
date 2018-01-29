package com.pracainzynierska.controller.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pracainzynierska.controller.helper.jsonObjects.templateList.TemplateJson;
import com.pracainzynierska.controller.helper.jsonObjects.templateList.TemplateJsonRoot;
import com.pracainzynierska.controller.helper.jsonObjects.treeData.*;
import com.pracainzynierska.controller.service.JsonBuilderService;
import com.pracainzynierska.model.entities.*;
import com.pracainzynierska.enums.NodeType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
@Service
public class JsonBuilderServiceImpl implements JsonBuilderService{
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
    public String buildFileResponse(String fileContent) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fileContent", fileContent);
        return new Gson().toJson(jsonObject);
    }

    @Override
    public String buildUrlResponse(String url) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("url", url);
        return new Gson().toJson(jsonObject);
    }

}
