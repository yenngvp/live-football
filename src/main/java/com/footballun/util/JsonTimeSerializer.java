/**
 * @author: yen.nt
 * @created on Dec 9, 2015
 */
package com.footballun.util;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author yen.nt
 *
 */
public class JsonTimeSerializer extends JsonSerializer<LocalTime> {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void serialize(LocalTime time, JsonGenerator generator,
                          SerializerProvider provider) throws IOException,
            JsonProcessingException {

        String timeString = time.format(formatter);
        generator.writeString(timeString);
    }
}
