package com.siddhiApi.util;

import com.siddhiApi.entity.CustomEvent;
import com.siddhiApi.entity.EventStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;

public class CustomEventToObjectArray {

    Logger logger = LoggerFactory.getLogger(CustomEventToObjectArray.class);

    public CustomEventToObjectArray(){}

    private Boolean checkEventAndStream(CustomEvent customEvent, EventStructure eventStructure){
        ArrayList<Boolean> conditionsToApply = new ArrayList<Boolean>();
        conditionsToApply.add(checkParameters(customEvent, eventStructure));
        //conditionsToApply.add(checkTypeOfParameters(customEvent, eventStructure));
        return conditionsToApply.stream().allMatch(val -> val == true);
    }

    private Boolean checkParameters(CustomEvent customEvent, EventStructure eventStructure){
        Set<String> eventSet = customEvent.getEventParameters().keySet();
        Set<String> structureSet = new HashSet<>(eventStructure.getParameters());

        return structureSet.equals(eventSet);
    }

    public Object[] parseCustomEventToObjectArray(CustomEvent customEvent, EventStructure eventStructure) throws Exception{
        if(!checkEventAndStream(customEvent, eventStructure))
            throw new Exception("Event is not of the same type as the stream");

        List<Object> objectList = new ArrayList<>();
        Map<String, String> typeOfParameters = eventStructure.getTypeOfParameters();
        Map<String, Object> eventParameters = customEvent.getEventParameters();

        for(String type: eventStructure.getParameters()){
            if (!(typeOfParameters.get(type)).equals("string")){
                try{
                    objectList.add(toNumeric(eventParameters.get(type).toString(), typeOfParameters.get(type)));
                }catch(Exception e){
                    throw e;
                }
            } else {
                objectList.add(eventParameters.get(type).toString());
            }
        }
        return objectList.toArray();
    }

    private static final Map<String, Function<String, Object>> parsers = new HashMap<String, Function<String, Object>>() {
        {
            put("float", (s) -> Float.parseFloat(s.substring(0, s.length()-1))); // (s) -> Float.parseFloat(s)
            put("long", (s) -> Long.parseLong(s.substring(0, s.length()-1))); // (s) -> Long.parseLong(s)
            put("double", Double::parseDouble);
            put("boolean", Boolean::parseBoolean);
            put("int", Integer::parseInt);
        }
    };

    private Object toNumeric(String strNum, String type) throws Exception{
        Object number;
        if (strNum == null) {
            throw new Exception("Null is not a valid value");
        }
        try {
            number = parsers.get(type).apply(strNum);
        } catch (NumberFormatException nfe) {
            throw new Exception("Cannot transform to number");
        }
        return number;
    }
}
