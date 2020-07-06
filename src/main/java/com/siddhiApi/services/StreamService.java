package com.siddhiApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.siddhiApi.entity.Stream;
import com.siddhiApi.entity.Subscription;
import com.siddhiApi.exceptions.DuplicatedEntity;
import com.siddhiApi.exceptions.NotFoundException;
import org.everit.json.schema.ValidationException;

import java.util.List;


public interface StreamService {
    void createStream(Stream stream) throws DuplicatedEntity;

    Stream getStream(String stream) throws NotFoundException;

    void removeStream(String stream) throws NotFoundException;

    void sendEvent(String stream, Object event) throws ValidationException, JsonProcessingException, NotFoundException;

    Subscription subscribe(String streamID, Subscription subscription) throws NotFoundException;

    List<Subscription> getSubscriptions(String streamID);

    String getStreamSubscriptionsToString(String streamID);
}
