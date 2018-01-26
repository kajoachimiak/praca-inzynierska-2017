package com.pracainzynierska.controller.helper.jsonObjects.templateList;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class TemplateJsonRoot {
    String ownerName;
    List<TemplateJson> templateList;

    public TemplateJsonRoot(String ownerName, List<TemplateJson> templateList) {
        this.ownerName = ownerName;
        this.templateList = templateList;
    }
}
