package com.siddhiApi.util;

import java.util.HashMap;
import java.util.Map;

public class PropertyOrderedDatabase {
    private Map<String, String[]> outputStreamPropertiesOrdered;
    private static PropertyOrderedDatabase propertyOrderedDatabase;

    private PropertyOrderedDatabase() {
        this.outputStreamPropertiesOrdered = new HashMap<>();
    }

    public static PropertyOrderedDatabase getPropertyOrderedDatabase() {
        if(propertyOrderedDatabase == null){
            propertyOrderedDatabase = new PropertyOrderedDatabase();
        }
        return propertyOrderedDatabase;
    }

    public void addOutputStreamPropertiesOrdered(String outputStreamName, String[] propertiesOrdered){
        outputStreamPropertiesOrdered.put(outputStreamName, propertiesOrdered);
    }

    public void removeOutputStreamPropertiesOrdered(String outputStreamName){
        outputStreamPropertiesOrdered.remove(outputStreamName);
    }
}
