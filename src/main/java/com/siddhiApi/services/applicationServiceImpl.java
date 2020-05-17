package com.siddhiApi.services;

import com.siddhiApi.dao.SiddhiDAO;
import com.siddhiApi.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class applicationServiceImpl implements applicationService{

    @Autowired
    private SiddhiDAO siddhiDAO;

    @Override
    public String runApp(String streamImplementation, String inputStreamName, String outputStreamName) {
        return siddhiDAO.runApp(streamImplementation, inputStreamName, outputStreamName);
    }

    @Override
    public List<String> getApplicationsRunning() {
        return siddhiDAO.getApplicationsRunning();
    }

    @Override
    public void stopApp(String streamName) {
        siddhiDAO.stopApp(streamName);
    }

    @Override
    public void sendEvent(String streamName, Event event) {
        siddhiDAO.sendEvent(streamName, event);
    }
}
