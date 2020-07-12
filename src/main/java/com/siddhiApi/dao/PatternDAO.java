package com.siddhiApi.dao;

import com.siddhiApi.entity.Pattern;
import com.siddhiApi.exceptions.NotFoundException;

import java.util.List;


public interface PatternDAO {
	void runPattern(Pattern pattern) throws Exception;

	Pattern[] getPatterns();

	Pattern getPattern(String id) throws NotFoundException;

	List<String> getPatternsRunning();

	void stopPattern(String patternName);
	
	void sendEvent(String streamName, Object[] event);
}

