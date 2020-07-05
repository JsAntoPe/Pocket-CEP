package com.siddhiApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siddhiApi.dao.*;
import com.siddhiApi.entity.Stream;
import com.siddhiApi.entity.Subscription;
import com.siddhiApi.exceptions.NotFoundException;
import com.siddhiApi.util.Parsers;
import com.siddhiApi.webhook.WebhookMediator;
import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class StreamServiceImpl implements StreamService{

    Logger logger = LoggerFactory.getLogger(StreamServiceImpl.class);

    @Autowired
    private final SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();

    @Autowired
    private final StreamDAO streamDAO = new StreamDAOImpl();

    @Autowired
    private final SiddhiDAO siddhiDAO = new SiddhiDAOImpl();

    @Override
    public void createStream(Stream stream) {
        streamDAO.createStream(stream);
    }

    @Override
    public Stream getStream(String stream) throws NotFoundException {
        return streamDAO.getStream(stream);
    }

    @Override
    public void sendEvent(String stream, Object event) throws ValidationException, JsonProcessingException, NotFoundException {
        JSONObject streamSchema = streamDAO.getStream(stream).getJsonSchema();

        ObjectMapper mapper = new ObjectMapper();
        JSONObject eventSchema = new JSONObject(mapper.writeValueAsString(event));

        Schema schema = SchemaLoader.load(streamSchema);
        schema.validate(eventSchema);

        Object[] eventParsed = Parsers.JsonObjectToObjectArray(eventSchema);
        for (Object object: eventParsed){
            logger.info("Property on event already parsed: " + object);
        }
        siddhiDAO.sendEvent(stream, eventParsed);
        List<Subscription> subscriptions = this.getSubscriptions(stream);
        if (subscriptions != null){
            WebhookMediator.webhookFromSubscription(subscriptions, event);
        }
    }

    public String subscribe(String streamID, Subscription subscription){
        return subscriptionDAO.subscribe(streamID, subscription);
    }

    @Override
    public List<Subscription> getSubscriptions(String streamID) {
        return subscriptionDAO.getSubscriptions(streamID);
    }

    @Override
    public String getStreamSubscriptionsToString(String streamID) {
        return subscriptionDAO.getSubscriptionsToString(streamID);
    }
}
