package com.siddhiApi.services;

import com.siddhiApi.dao.SiddhiDAO;
import com.siddhiApi.entity.Pattern;
import com.siddhiApi.exceptions.NotFoundException;
import com.siddhiApi.exceptions.PropertyNotFoundOnSelect;
import com.siddhiApi.util.PatternCodeChecker;
import com.siddhiApi.util.PatternCodeGeneratorMediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatternServiceImpl implements PatternService {

    private Logger logger = LoggerFactory.getLogger(PatternServiceImpl.class);

    @Autowired
    private SiddhiDAO siddhiDAO;

    /*@Autowired
    private StreamStructureDAO streamStructureDAO;*/

    @Override
    public void runPattern(Pattern pattern) throws Exception {
        logger.info(pattern.getPatternCode());
        pattern.setPatternCode(PatternCodeGeneratorMediator.getFullApplicationCode(pattern));
        PatternCodeChecker.outputStreamCheck(pattern);
        siddhiDAO.runPattern(pattern);
    }

    @Override
    public List<String> getPatternsRunning() {
        return siddhiDAO.getPatternsRunning();
    }

    @Override
    public void stopPattern(String appName) {
        siddhiDAO.stopPattern(appName);
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
