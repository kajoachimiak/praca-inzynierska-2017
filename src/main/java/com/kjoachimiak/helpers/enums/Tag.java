package com.kjoachimiak.helpers.enums;

/**
 * Created by karol on 28.01.18.
 */
public enum Tag {
    USER("%USER%"),
    GROUP("%GROUP%"),
    EDITION("%EDITION%"),
    COURSE("%COURSE%"),
    ENV("%ENV\\(([^)]+)\\)%"),
    DATE("%DATE\\(([^)]+)\\)%");

    private String code;

    Tag(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static TemplateType fromCode(String code) {
        for (TemplateType status :TemplateType.values()){
            if (status.getCode().equals(code)){
                return status;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!");
    }
}
