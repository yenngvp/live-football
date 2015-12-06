package com.footballun.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

/**
 * Created by YenNguyen on 12/6/15.
 */
@Converter
public class LocalTimePersistenceConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public java.sql.Time convertToDatabaseColumn(LocalTime entityValue) {
        if (entityValue != null) {
            return java.sql.Time.valueOf(entityValue);
        }
        return null;
    }

    @Override
    public LocalTime convertToEntityAttribute(java.sql.Time databaseValue) {
        if (databaseValue != null) {
            return databaseValue.toLocalTime();
        }
        return null;
    }
}
