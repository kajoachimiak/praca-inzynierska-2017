package com.pracainzynierska.controller.helper.jsonObjects.treeData;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class User {
    String type;
    String name;
    List<String> list;

    public User() {
    }

    public User(String type, String name, List<String> list) {
        this.type = type;
        this.name = name;
        this.list = list;
    }
}
