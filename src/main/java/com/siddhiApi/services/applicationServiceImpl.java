package com.siddhiApi.services;

import com.siddhiApi.dao.SiddhiDAO;
import com.siddhiApi.dao.StreamStructureDao;
import com.siddhiApi.entity.Event;
import com.siddhiApi.entity.EventStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class applicationServiceImpl implements applicationService{

    private Logger logger = LoggerFactory.getLogger(applicationServiceImpl.class);

    @Autowired
    private SiddhiDAO siddhiDAO;

    @Autowired
    private StreamStructureDao streamStructureDao;

    @Override
    public Boolean runApp(String streamImplementation, String inputStreamName, String outputStreamName) {
        Boolean successfulRun = siddhiDAO.runApp(streamImplementation, inputStreamName, outputStreamName);
        logger.info("SuccesfulRun: " + successfulRun);
        if (successfulRun){
            streamStructureDao.createStructure(streamImplementation, inputStreamName);
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
        streamStructureDao.removeStructure(streamName);
    }

    @Override
    public void sendEvent(String streamName, Event event) {
        /*EventStructure eventStructure = streamStructureDao.getStructure(streamName);
        siddhiDAO.sendEvent(streamName, event);*/
    }
}
