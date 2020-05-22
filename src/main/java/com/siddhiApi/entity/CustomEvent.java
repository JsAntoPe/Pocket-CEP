package com.siddhiApi.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;


public class CustomEvent {
    Logger logger = LoggerFactory.getLogger(CustomEvent.class);
    Map<String, Object> eventParameters = new LinkedHashMap<>();

    @JsonAnySetter
    void setEventParameters(String parameter, Object value){
        eventParameters.put(parameter, value);
    }

    public Map<String, Object> getEventParameters() {
        return eventParameters;
    }

    public void setEventParameters(Map<String, Object> eventParameters) {
        this.eventParameters = eventParameters;
    }
}
