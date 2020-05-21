package com.siddhiApi.structureDatabase;

import com.siddhiApi.entity.EventStructure;

import java.util.HashMap;

public class StructureDatabase {
    private static StructureDatabase structureDatabase;
    private static HashMap<String, EventStructure> streamToEventStructure;

    private StructureDatabase(){}

    public static StructureDatabase getStructureDatabase(){
        if(structureDatabase == null){
            structureDatabase = new StructureDatabase();
            streamToEventStructure = new HashMap<>();
        }
        return structureDatabase;
    }

    public void addEventStructure(String stream, EventStructure evst){
        streamToEventStructure.put(stream, evst);
    }

    public EventStructure getEventStructure(String stream){
        return streamToEventStructure.get(stream);
    }

    public void removeEventStructure(String stream){
        streamToEventStructure.remove(stream);
    }
}
