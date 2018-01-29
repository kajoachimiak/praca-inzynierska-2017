package com.pracainzynierska.controller.service.impl;

import com.pracainzynierska.controller.service.TemplateListGenerator;
import com.pracainzynierska.model.entities.*;
import com.pracainzynierska.enums.NodeType;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
@Service
public class TemplateListGeneratorImpl implements TemplateListGenerator {

    @Override
    public Pair<String, List<Template>> generateTemplateList(NodeType nodeType, User user) {
        List<Template> templateList = new ArrayList<>();
        String templateOwner = "";
        Group group = user.getGroup();
        Edition edition = group.getEdition();
        Course course = edition.getCourse();
        switch (nodeType) {
            case USER:
                templateList.addAll(user.getTemplateList());
                templateOwner = user.getLogin();
                break;
            case GROUP:
                templateList.addAll(group.getTemplateList());
                templateOwner = group.getName();
                break;
            case EDITION:
                templateList.addAll(edition.getTemplateList());
                templateOwner = edition.getName();
                break;
            case COURSE:
                templateList.addAll(course.getTemplateList());
                templateOwner = course.getName();
                break;

        }
        return new Pair<>(templateOwner, templateList);
    }
}
