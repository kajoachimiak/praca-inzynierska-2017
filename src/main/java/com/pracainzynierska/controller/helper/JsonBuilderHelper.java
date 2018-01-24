package com.pracainzynierska.controller.helper;

import com.pracainzynierska.controller.helper.jsonObjects.Course;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class JsonBuilderHelper {
    List<Course> courseList;

    public JsonBuilderHelper(List<Course> courseList) {
        this.courseList = courseList;
    }

    public JsonBuilderHelper() {
    }
}
