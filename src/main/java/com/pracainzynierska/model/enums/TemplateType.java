package com.pracainzynierska.model.enums;

/**
 * Created by karol on 26.01.18.
 */
public enum TemplateType {
    SCRIPT("SCRIPT"),
    FILE("FILE"),
    URL("URL");

    private String code;

    TemplateType(String code) {
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
