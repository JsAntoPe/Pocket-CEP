package com.siddhiApi.apiRest;

import com.siddhiApi.entity.Event;

import java.util.List;


public interface SiddhiDAO {
	String runApp(String streamImplementation, String streamName);

	List<String> getApplicationsRunning();

	void stopApp(String streamName);
	
	void sendEvent(String streamName, Event event);
}

