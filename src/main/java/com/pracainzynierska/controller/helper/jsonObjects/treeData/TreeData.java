package com.pracainzynierska.controller.helper.jsonObjects.treeData;

import com.pracainzynierska.controller.helper.jsonObjects.treeData.Course;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class TreeData {
    List<Course> list;

    public TreeData(List<Course> courseList) {
        this.list = courseList;
    }

    public TreeData() {
    }
}
