package com.siddhiApi.dao;

import com.siddhiApi.entity.Pattern;

import java.util.List;


public interface SiddhiDAO {
	void runPattern(Pattern pattern) throws Exception;

	List<String> getPatternsRunning();

	void stopPattern(String patternName);
	
	void sendEvent(String streamName, Object[] event);
}

