package com.siddhiApi.dao;

import com.siddhiApi.entity.EventStructure;
import com.siddhiApi.structureDatabase.StructureDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StreamStructureDAOImpl implements StreamStructureDao{
    Logger logger = LoggerFactory.getLogger(StreamStructureDAOImpl.class);
    StructureDatabase structureDatabase = StructureDatabase.getStructureDatabase();

    @Override
    public void createStructure(String inputStreamName, String application) {
        EventStructure eventStructure = new EventStructure(inputStreamName, application);
        logger.info("Stream Name: " + inputStreamName);
        structureDatabase.addEventStructure(inputStreamName, eventStructure);
    }

    @Override
    public EventStructure getStructure(String inputStreamName) {
        return structureDatabase.getEventStructure(inputStreamName);
    }

    @Override
    public void removeStructure(String inputStreamName) {
        structureDatabase.removeEventStructure(inputStreamName);
    }
}
