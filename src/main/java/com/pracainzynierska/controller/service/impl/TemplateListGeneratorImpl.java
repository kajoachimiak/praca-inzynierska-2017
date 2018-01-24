package com.pracainzynierska.controller.service.impl;

import com.pracainzynierska.controller.service.TemplateListGenerator;
import com.pracainzynierska.model.entities.*;
import com.pracainzynierska.model.enums.NodeType;
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
    public Pair<String,List<Szablony>> generateTemplateList(NodeType nodeType, Uczestnik user) {
        List<Szablony> templateList = new ArrayList<>();
        String templateOwner = "";
        Grupa grupa = user.getGrupa();
        Edycja edycja = grupa.getEdycja();
        Przedmiot przedmiot = edycja.getPrzedmiot();
        switch (nodeType){
            case GROUP:
                templateList.addAll(grupa.getSzablonyList());
                templateOwner = grupa.getNazwa();
                break;
            case EDITION:
                templateList.addAll(edycja.getSzablonyList());
                templateOwner = edycja.getNazwa();
                break;
            case COURSE:
                templateList.addAll(przedmiot.getSzablonyList());
                templateOwner = przedmiot.getNazwa();
                break;

        }
        return new Pair<>(templateOwner, templateList);
    }
}
