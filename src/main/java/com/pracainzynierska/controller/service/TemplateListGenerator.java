package com.pracainzynierska.controller.service;

import com.pracainzynierska.model.entities.Template;
import com.pracainzynierska.model.entities.User;
import com.pracainzynierska.enums.NodeType;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public interface TemplateListGenerator {
    Pair<String,List<Template>> generateTemplateList(NodeType nodeType, User user);
}
