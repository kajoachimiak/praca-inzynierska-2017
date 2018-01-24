package com.pracainzynierska.controller.helper.jsonObjects;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class Edition {
    String name;
    List<Group> groupList;

    public Edition() {
    }

    public Edition(String name, List<Group> groupList) {
        this.name = name;
        this.groupList = groupList;
    }
}
