package com.pracainzynierska.controller.service;

import com.pracainzynierska.model.entities.Szablony;
import com.pracainzynierska.model.entities.Uczestnik;
import com.pracainzynierska.model.enums.NodeType;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public interface TemplateListGenerator {
    Pair<String,List<Szablony>> generateTemplateList(NodeType nodeType, Uczestnik user);
}
