package com.siddhiApi.util;

import com.mchange.v1.lang.BooleanUtils;
import com.siddhiApi.entity.CustomEvent;
import com.siddhiApi.entity.EventStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;

public class CustomEventToObjectArray {

    Logger logger = LoggerFactory.getLogger(CustomEventToObjectArray.class);

    public CustomEventToObjectArray(){}

    public Boolean checkEventAndStream(CustomEvent customEvent, EventStructure eventStructure){
        ArrayList<Boolean> conditionsToApply = new ArrayList<Boolean>();
        conditionsToApply.add(checkParameters(customEvent, eventStructure));
        conditionsToApply.add(checkTypeOfParameters(customEvent, eventStructure));
        return conditionsToApply.stream().allMatch(val -> {
            logger.info("Val: " + val);
            return val == true;
        });
    }

    private Boolean checkParameters(CustomEvent customEvent, EventStructure eventStructure){
        Set<String> eventSet = customEvent.getEventParameters().keySet();
        Set<String> structureSet = new HashSet<>(eventStructure.getParameters());
        return structureSet.removeAll(eventSet);
    }

    private Boolean checkTypeOfParameters(CustomEvent customEvent, EventStructure eventStructure){
        Map<String, String> typeOfParameters = eventStructure.getTypeOfParameters();
        Map<String, Object> eventParameters = customEvent.getEventParameters();
        for(String type: typeOfParameters.keySet()){
            if (!(typeOfParameters.get(type)).equals("string") && !isNumeric(eventParameters.get(type).toString(), typeOfParameters.get(type))){
                logger.info("El parametro no coincide.");
                return false;
            }
        }
        return true;
    }

    private static final Map<String, Function<String, Object>> parsers = new HashMap<String, Function<String, Object>>() {
        {
            put("float", Float::parseFloat); // (s) -> Float.parseFloat(s)
            put("long", Long::parseLong); // (s) -> Long.parseLong(s)
        }
    };

    private Boolean isNumeric(String strNum, String type) {
        if (strNum == null) {
            return false;
        }
        try {
            parsers.get(type).apply(strNum.substring(0, strNum.length()-1));
        } catch (NumberFormatException nfe) {
            logger.info("Llego");
            return false;
        }
        return true;
    }
}
