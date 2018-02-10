package com.kjoachimiak.helpers.jsonObjects.treeData;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class CourseJson {
    String type;
    String name;
    List<EditionJson> list;

    public CourseJson() {
    }

    public CourseJson(String type, String name, List<EditionJson> list) {
        this.type = type;
        this.name = name;
        this.list = list;
    }
}
