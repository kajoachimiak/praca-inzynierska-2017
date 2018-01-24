package com.pracainzynierska.controller.helper.jsonObjects;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class Course {
    String name;
    List<Edition> editionList;

    public Course() {
    }

    public Course(String name, List<Edition> editionList) {
        this.name = name;
        this.editionList = editionList;
    }
}
