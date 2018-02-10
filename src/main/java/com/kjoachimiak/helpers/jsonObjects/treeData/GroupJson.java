package com.kjoachimiak.helpers.jsonObjects.treeData;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class GroupJson {
    String type;
    String name;
    List<UserJson> list;

    public GroupJson() {
    }

    public GroupJson(String type, String name, List<UserJson> list) {
        this.type = type;
        this.name = name;
        this.list = list;
    }
}
