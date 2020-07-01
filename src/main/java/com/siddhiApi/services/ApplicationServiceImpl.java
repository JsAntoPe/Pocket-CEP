package com.siddhiApi.services;

import com.siddhiApi.dao.SiddhiDAO;
import com.siddhiApi.dao.StreamStructureDAO;
import com.siddhiApi.entity.CustomEvent;
import com.siddhiApi.entity.EventStructure;
import com.siddhiApi.util.CustomEventToObjectArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    @Autowired
    private SiddhiDAO siddhiDAO;

    @Autowired
    private StreamStructureDAO streamStructureDAO;

    @Override
    public Boolean runApp(String streamImplementation, String inputStreamName, String outputStreamName) {
        Boolean successfulRun = siddhiDAO.runApp(streamImplementation, inputStreamName, outputStreamName);
        logger.info("SuccesfulRun: " + successfulRun);
        if (successfulRun){
            streamStructureDAO.createStructure(inputStreamName, streamImplementation);
        }
        return successfulRun;
    }

    @Override
    public List<String> getApplicationsRunning() {
        return siddhiDAO.getApplicationsRunning();
    }

    @Override
    public void stopApp(String streamName) {
        siddhiDAO.stopApp(streamName);
        streamStructureDAO.removeStructure(streamName);
    }

    @Override
    public void sendEvent(String streamName, CustomEvent event) throws Exception{
        EventStructure eventStructure = streamStructureDAO.getStructure(streamName);
        Object[] arrayFormedEvent;
        try{
            arrayFormedEvent = new CustomEventToObjectArray().parseCustomEventToObjectArray(event, eventStructure);
        }catch (Exception e){
            throw e;
        }
        siddhiDAO.sendEvent(streamName, arrayFormedEvent);
    }
}
