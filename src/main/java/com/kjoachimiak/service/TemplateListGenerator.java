package com.kjoachimiak.service;

import com.kjoachimiak.model.entities.Template;
import com.kjoachimiak.model.entities.User;
import com.kjoachimiak.helpers.enums.NodeType;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public interface TemplateListGenerator {
    Pair<String,List<Template>> generateTemplateList(NodeType nodeType, User user);
}
