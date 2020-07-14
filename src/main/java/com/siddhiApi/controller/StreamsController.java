package com.siddhiApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.siddhiApi.entity.Stream;
import com.siddhiApi.entity.Subscription;
import com.siddhiApi.exceptions.DuplicatedEntity;
import com.siddhiApi.exceptions.NotFoundException;
import com.siddhiApi.exceptions.StreamOnUseException;
import com.siddhiApi.services.StreamService;

import org.everit.json.schema.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1/streams")
public class StreamsController {

    Logger logger = LoggerFactory.getLogger(StreamsController.class);

    @Autowired
    private StreamService streamService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createStream(@RequestBody Stream stream){
        try {
            streamService.createStream(stream);
        } catch (DuplicatedEntity duplicatedEntity) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null , duplicatedEntity);
        }
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStream(@PathVariable String name){
        try {
            return streamService.getStream(name).getJsonSchema().toString();
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The stream could not be found." , e);
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "/{name}")
    public void removeStream(@PathVariable String name){
        try {
            streamService.removeStream(name);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The stream could not be found." , e);
        } catch (StreamOnUseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{streamID}/events")
    public void sendEvent(@PathVariable String streamID, @RequestBody Object event){
        logger.info("Printing event schema: " + event);
        try {
            streamService.sendEvent(streamID, event);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Incorrect JSON format", e);
        } catch (ValidationException ve){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The event sent does not match with the stream structure.", ve);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The stream could not be found." , e);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{streamID}/subscriptions")
    public Subscription subscribe(@PathVariable String streamID, @RequestBody Subscription subscription){
        try {
            return streamService.subscribe(streamID, subscription);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The subscription could not be found." , e);
        }
    }

    @GetMapping(value = "/{streamID}/subscriptions")
    public Subscription[] getStreamSubscriptions(@PathVariable String streamID){
        return streamService.getSubscriptions(streamID);
    }

    @GetMapping(value = "/{streamID}/subscriptions/{id}")
    public Subscription getStreamSubscription(@PathVariable String streamID, @PathVariable String id){
        try {
            return streamService.getSubscription(streamID, id);
        } catch (NotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The resource could not be found." , notFoundException);
        }
    }

    @DeleteMapping(value = "/{streamID}/subscriptions/{id}")
    public void deleteStreamSubscription(@PathVariable String streamID, @PathVariable String id){
        try {
            streamService.unsubscribe(streamID, id);
        } catch (NotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The resource could not be found." , notFoundException);
        }
    }

    @PutMapping(value = "/{streamID}/subscriptions/{subscriptionID}")
    public void updateStreamSubscription(@PathVariable String streamID, @PathVariable String subscriptionID, @RequestBody Subscription subscription){
        try {
            streamService.updateSubscription(streamID, subscriptionID, subscription);
        } catch (NotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The resource could not be found." , notFoundException);
        }
    }
}
