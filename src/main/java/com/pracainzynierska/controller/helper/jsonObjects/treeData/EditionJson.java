package com.pracainzynierska.controller.helper.jsonObjects.treeData;

import java.util.List;

/**
 * Created by karol on 24.01.18.
 */
public class EditionJson {
    String type;
    String name;
    List<GroupJson> list;

    public EditionJson() {
    }

    public EditionJson(String type, String name, List<GroupJson> list) {
        this.type = type;
        this.name = name;
        this.list = list;
    }
}
