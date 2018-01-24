package com.pracainzynierska.controller.helper.jsonObjects;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class Group {
    String name;
    List<User> userList;

    public Group() {
    }

    public Group(String name, List<User> userList) {
        this.name = name;
        this.userList = userList;
    }
}
