package com.siddhiApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.siddhiApi.entity.Stream;
import org.everit.json.schema.ValidationException;


public interface StreamService {
    void createStream(Stream stream);

    Stream getStream(String stream);

    void sendEvent(String stream, Object event) throws ValidationException, JsonProcessingException;
}
