package com.kjoachimiak.helpers.jsonObjects.treeData;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class TreeData {
    List<CourseJson> list;

    public TreeData(List<CourseJson> courseJsonList) {
        this.list = courseJsonList;
    }

    public TreeData() {
    }
}
