package com.pracainzynierska.controller.helper.jsonObjects.treeData;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class Group {
    String type;
    String name;
    List<User> list;

    public Group() {
    }

    public Group(String type, String name, List<User> list) {
        this.type = type;
        this.name = name;
        this.list = list;
    }
}
