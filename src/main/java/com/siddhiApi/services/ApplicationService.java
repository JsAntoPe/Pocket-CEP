package com.siddhiApi.services;

import com.siddhiApi.entity.CustomEvent;

import java.util.List;

public interface ApplicationService {
    Boolean runApp(String streamImplementation, String inputStreamName, String outputStreamName);

    List<String> getApplicationsRunning();

    void stopApp(String streamName);

    void sendEvent(String streamName, CustomEvent event) throws Exception;
}
