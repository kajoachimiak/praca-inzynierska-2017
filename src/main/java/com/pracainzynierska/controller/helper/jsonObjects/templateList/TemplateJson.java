package com.pracainzynierska.controller.helper.jsonObjects.templateList;

/**
 * Created by karol on 24.01.18.
 */
public class TemplateJson {
    private Integer id;
    private String name;
    private String content;
    private String description;
    private String type;

    public TemplateJson(Integer id, String name, String content, String description, String type) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.description = description;
        this.type = type;
    }
}
