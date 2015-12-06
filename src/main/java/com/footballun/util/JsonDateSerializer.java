package com.footballun.util;

/**
 * Created by YenNguyen on 12/6/15.
 */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by YenNguyen on 12/6/15.
 */
public class JsonDateSerializer extends JsonSerializer<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void serialize(LocalDate date, JsonGenerator generator,
                          SerializerProvider provider) throws IOException,
            JsonProcessingException {

        String dateString = date.format(formatter);
//        String dateString = String.valueOf(date.toEpochDay());
        generator.writeString(dateString);
    }
}