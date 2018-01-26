package com.pracainzynierska.controller.service.impl;

import com.google.gson.Gson;
import com.pracainzynierska.controller.helper.jsonObjects.templateList.TemplateRoot;
import com.pracainzynierska.controller.helper.jsonObjects.treeData.*;
import com.pracainzynierska.model.entities.*;
import com.pracainzynierska.model.entities.Course;
import com.pracainzynierska.model.entities.Edition;
import com.pracainzynierska.model.entities.Group;
import com.pracainzynierska.model.entities.User;
import com.pracainzynierska.model.enums.NodeType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
@Service
public class JsonBuilderService {
    public String buildUserRelationsResponse(User user){
        Group group = user.getGroup();
        Edition edition = group.getEdition();
        Course course = edition.getCourse();

        List<com.pracainzynierska.controller.helper.jsonObjects.treeData.Course> courses = new ArrayList<>();
        List<com.pracainzynierska.controller.helper.jsonObjects.treeData.Edition> editions = new ArrayList<>();
        List<com.pracainzynierska.controller.helper.jsonObjects.treeData.Group> groups = new ArrayList<>();
        List<com.pracainzynierska.controller.helper.jsonObjects.treeData.User> users = new ArrayList<>();

        users.add(new com.pracainzynierska.controller.helper.jsonObjects.treeData.User(NodeType.USER.toString(), user.getLogin(),new ArrayList<>()));
        groups.add(new com.pracainzynierska.controller.helper.jsonObjects.treeData.Group(NodeType.GROUP.toString(), group.getName(),users));
        editions.add(new com.pracainzynierska.controller.helper.jsonObjects.treeData.Edition(NodeType.EDITION.toString(), edition.getName(), groups));
        courses.add(new com.pracainzynierska.controller.helper.jsonObjects.treeData.Course(NodeType.COURSE.toString(), course.getName(), editions));

        TreeData treeData = new TreeData(courses);

        return new Gson().toJson(treeData);
    }
    public String buildTemplateListResponse(String ownerName, List<Template> templateList){
        List<com.pracainzynierska.controller.helper.jsonObjects.templateList.Template> resultTemplateList = new ArrayList<>();
        templateList.forEach(template -> {
            resultTemplateList.add(new com.pracainzynierska.controller.helper.jsonObjects.templateList.Template(template.getName(), template.getContent(), template.getDescription()));
        });
        TemplateRoot templateRoot = new TemplateRoot(ownerName, resultTemplateList);
        return new Gson().toJson(templateRoot);
    }
}
