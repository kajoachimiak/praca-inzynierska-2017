package com.pracainzynierska.controller.helper.jsonObjects.templateList;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class TemplateRoot {
    String ownerName;
    List<Template> templateList;

    public TemplateRoot(String ownerName, List<Template> templateList) {
        this.ownerName = ownerName;
        this.templateList = templateList;
    }
}
