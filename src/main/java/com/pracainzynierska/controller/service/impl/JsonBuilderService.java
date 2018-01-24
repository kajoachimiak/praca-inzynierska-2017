package com.pracainzynierska.controller.service.impl;

import com.google.gson.Gson;
import com.pracainzynierska.controller.helper.jsonObjects.templateList.Template;
import com.pracainzynierska.controller.helper.jsonObjects.templateList.TemplateRoot;
import com.pracainzynierska.controller.helper.jsonObjects.treeData.*;
import com.pracainzynierska.model.entities.*;
import com.pracainzynierska.model.enums.NodeType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
@Service
public class JsonBuilderService {
    public String buildUserRelationsResponse(Uczestnik user){
        Grupa grupa = user.getGrupa();
        Edycja edycja = grupa.getEdycja();
        Przedmiot przedmiot = edycja.getPrzedmiot();

        List<Course> courses = new ArrayList<>();
        List<Edition> editions = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        List<User> users = new ArrayList<>();

        users.add(new User(NodeType.USER.toString(), user.getLogin(),new ArrayList<>()));
        groups.add(new Group(NodeType.GROUP.toString(),grupa.getNazwa(),users));
        editions.add(new Edition(NodeType.EDITION.toString(), edycja.getNazwa(), groups));
        courses.add(new Course(NodeType.COURSE.toString(), przedmiot.getNazwa(), editions));

        TreeData treeData = new TreeData(courses);

        return new Gson().toJson(treeData);
    }
    public String buildTemplateListResponse(String ownerName, List<Szablony> templateList){
        List<Template> resultTemplateList = new ArrayList<>();
        templateList.forEach(szablony -> {
            resultTemplateList.add(new Template(szablony.getNazwa(), szablony.getTresc(), szablony.getOpis()));
        });
        TemplateRoot templateRoot = new TemplateRoot(ownerName, resultTemplateList);
        return new Gson().toJson(templateRoot);
    }
}
