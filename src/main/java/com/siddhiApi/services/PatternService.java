package com.siddhiApi.services;

import com.siddhiApi.entity.Pattern;

import java.util.List;

public interface PatternService {
    void runPattern(Pattern pattern) throws Exception;

    List<String> getPatternsRunning();

    void stopPattern(String patternName);

    //void sendEvent(String streamName, CustomEvent event) throws Exception;
}
