package com.pracainzynierska.controller.helper.jsonObjects.treeData;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class Course {
    String type;
    String name;
    List<Edition> list;

    public Course() {
    }

    public Course(String type, String name, List<Edition> list) {
        this.type = type;
        this.name = name;
        this.list = list;
    }
}
