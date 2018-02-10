package com.kjoachimiak.helpers.jsonObjects.treeData;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class UserJson {
    String type;
    String name;
    List<String> list;

    public UserJson() {
    }

    public UserJson(String type, String name, List<String> list) {
        this.type = type;
        this.name = name;
        this.list = list;
    }
}
