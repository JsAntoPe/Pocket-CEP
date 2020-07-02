package com.siddhiApi.util;

import com.siddhiApi.entity.Application;
import com.siddhiApi.services.StreamService;
import com.siddhiApi.services.StreamServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationCodeGeneratorMediator {

    static Logger logger = LoggerFactory.getLogger(ApplicationCodeGeneratorMediator.class);

    private static final StreamService streamService = new StreamServiceImpl();

    private static final StreamGenerator streamGenerator = new StreamGeneratorSiddhi();

    public static String getFullApplicationCode(Application application){
        String applicationCode = "";
        for(String inputStreamName: application.getInputStreamNames()){
            logger.info("InputStreamName: " + inputStreamName);
            logger.info("InputStreamName from stream itself: " + streamService.getStream(inputStreamName).getStreamID());
            applicationCode += streamGenerator.generateCode(streamService.getStream(inputStreamName));
        }
        applicationCode += application.getApplicationCode();
        //applicationCode += streamGenerator.generateCode(streamService.getStream(application.getOutputStreamName()));

        logger.info("Application code: " + applicationCode);
        return applicationCode;
    }
}
