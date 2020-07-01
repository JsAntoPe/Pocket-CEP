package com.siddhiApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.siddhiApi.entity.Stream;
import com.siddhiApi.services.StreamService;

import org.everit.json.schema.ValidationException;
import org.json.JSONObject;

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

    @PostMapping()
    public void createStream(@RequestBody Stream stream){
        streamService.createStream(stream);
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStream(@PathVariable String name){
        return streamService.getStream(name).getJsonSchema().toString();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{stream}/events")
    public void sendEvent(@PathVariable String stream, @RequestBody Object event){
        logger.info("Printing event schema: " + event);
        try {
            streamService.sendEvent(stream, event);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Incorrect JSON format", e);
        } catch (ValidationException ve){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The event sent does not match with the stream structure.", ve);
        }
    }
}
