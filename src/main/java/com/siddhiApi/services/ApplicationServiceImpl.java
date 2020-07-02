package com.siddhiApi.services;

import com.siddhiApi.dao.SiddhiDAO;
import com.siddhiApi.entity.Application;
import com.siddhiApi.util.ApplicationCodeGeneratorMediator;
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

    /*@Autowired
    private StreamStructureDAO streamStructureDAO;*/

    @Override
    public void runApp(Application application) throws Exception {
        logger.info(application.getApplicationCode());
        application.setApplicationCode(ApplicationCodeGeneratorMediator.getFullApplicationCode(application));
        siddhiDAO.runApp(application);
    }

    @Override
    public List<String> getApplicationsRunning() {
        return siddhiDAO.getApplicationsRunning();
    }

    @Override
    public void stopApp(String appName) {
        siddhiDAO.stopApp(appName);
        //streamStructureDAO.removeStructure(streamName);
    }

    /*@Override
    public void sendEvent(String streamName, CustomEvent event) throws Exception{
        EventStructure eventStructure = streamStructureDAO.getStructure(streamName);
        Object[] arrayFormedEvent;
        try{
            arrayFormedEvent = new CustomEventToObjectArray().parseCustomEventToObjectArray(event, eventStructure);
        }catch (Exception e){
            throw e;
        }
        siddhiDAO.sendEvent(streamName, arrayFormedEvent);
    }*/
}
