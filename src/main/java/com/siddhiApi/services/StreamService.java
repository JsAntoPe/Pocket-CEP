package com.siddhiApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.siddhiApi.entity.Stream;
import com.siddhiApi.entity.Subscription;
import org.everit.json.schema.ValidationException;


public interface StreamService {
    void createStream(Stream stream);

    Stream getStream(String stream);

    void sendEvent(String stream, Object event) throws ValidationException, JsonProcessingException;

    String subscribe(String streamID, Subscription subscription);

    String getStreamSubscriptions(String streamID);
}
