package com.siddhiApi.dao;

import com.siddhiApi.entity.EventStructure;
import com.siddhiApi.structureDatabase.StructureDatabase;
import org.springframework.stereotype.Service;

@Service
public class StreamStructureDAOImpl implements StreamStructureDao{
    StructureDatabase structureDatabase = StructureDatabase.getStructureDatabase();

    @Override
    public void createStructure(String inputStreamName, String application) {
        EventStructure eventStructure = new EventStructure(inputStreamName, application);
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
