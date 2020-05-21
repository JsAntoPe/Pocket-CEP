package com.siddhiApi.services;

import com.siddhiApi.entity.Event;

import java.util.List;

public interface applicationService {
    Boolean runApp(String streamImplementation, String inputStreamName, String outputStreamName);

    List<String> getApplicationsRunning();

    void stopApp(String streamName);

    void sendEvent(String streamName, Event event);
}
