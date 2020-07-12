package com.siddhiApi.services;

import com.siddhiApi.entity.Pattern;
import com.siddhiApi.exceptions.NotFoundException;

import java.util.List;

public interface PatternService {
    void runPattern(Pattern pattern) throws Exception;

    List<String> getPatternsRunning();

    void stopPattern(String patternName);

    Pattern[] getPatterns();

    Pattern getPattern(String id) throws NotFoundException;

    //void sendEvent(String streamName, CustomEvent event) throws Exception;
}
