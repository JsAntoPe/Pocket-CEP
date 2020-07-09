package com.siddhiApi.services;

import com.siddhiApi.dao.PatternDAO;
import com.siddhiApi.entity.Pattern;
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
    private PatternDAO patternDAO;

    /*@Autowired
    private StreamStructureDAO streamStructureDAO;*/

    @Override
    public void runPattern(Pattern pattern) throws Exception {
        /*logger.info(pattern.getPatternCode());

        JSONObject patternSchema = new JSONObject("src/main/resources/PatternJSONSchema");

        ObjectMapper mapper = new ObjectMapper();
        JSONObject introducedPatternSchema = new JSONObject(mapper.writeValueAsString(pattern));
        logger.info("Introduced Pattern Schema: " + introducedPatternSchema);
        Schema schema = SchemaLoader.load(patternSchema);
        schema.validate(introducedPatternSchema);
        */
        pattern.setPatternCode(PatternCodeGeneratorMediator.getFullApplicationCode(pattern));
        PatternCodeChecker.outputStreamCheck(pattern);
        patternDAO.runPattern(pattern);
    }

    @Override
    public List<String> getPatternsRunning() {
        return patternDAO.getPatternsRunning();
    }

    @Override
    public void stopPattern(String appName) {
        patternDAO.stopPattern(appName);
        //streamStructureDAO.removeStructure(streamName);
    }

    @Override
    public List<Pattern> getPatterns() {
        return patternDAO.getPatterns();
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
