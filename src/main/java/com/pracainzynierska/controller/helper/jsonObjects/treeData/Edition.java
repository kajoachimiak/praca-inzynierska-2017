package com.pracainzynierska.controller.helper.jsonObjects.treeData;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class Edition {
    String type;
    String name;
    List<Group> list;

    public Edition() {
    }

    public Edition(String type, String name, List<Group> list) {
        this.type = type;
        this.name = name;
        this.list = list;
    }
}
