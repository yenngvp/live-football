/**
 * @author: yen.nt
 * @created on Dec 9, 2015
 */
package com.footballun.util;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

/**
 * @author yen.nt
 *
 */
public class JsonTimeDeserializer extends JsonDeserializer<LocalTime> {

	@Override
    public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        ObjectCodec oc = jp.getCodec();
        TextNode node = (TextNode) oc.readTree(jp);
        String timeString = node.textValue();

        Instant instant = Instant.parse(timeString);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalTime date = LocalTime.of(dateTime.getHour(), dateTime.getMinute());
        return date;
    }
}
