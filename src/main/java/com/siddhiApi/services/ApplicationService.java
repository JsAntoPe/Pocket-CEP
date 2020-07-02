package com.siddhiApi.services;

import com.siddhiApi.entity.Application;
import com.siddhiApi.entity.CustomEvent;

import java.util.List;

public interface ApplicationService {
    void runApp(Application application) throws Exception;

    List<String> getApplicationsRunning();

    void stopApp(String streamName);

    //void sendEvent(String streamName, CustomEvent event) throws Exception;
}
