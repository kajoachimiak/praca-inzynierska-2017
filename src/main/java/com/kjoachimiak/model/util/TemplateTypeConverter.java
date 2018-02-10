package com.kjoachimiak.model.util;

import com.kjoachimiak.helpers.enums.TemplateType;

import javax.persistence.AttributeConverter;

/**
 * Created by karol on 26.01.18.
 */
public class TemplateTypeConverter implements AttributeConverter<TemplateType, String> {
    @Override
    public String convertToDatabaseColumn(TemplateType value) {
        if ( value == null ) {
            return null;
        }

        return value.getCode();    }

    @Override
    public TemplateType convertToEntityAttribute(String value) {
        if ( value == null ) {
            return null;
        }

        return TemplateType.fromCode(value);
    }
}
