package com.siddhiApi.dao;

import com.siddhiApi.entity.Event;

import java.util.List;


public interface SiddhiDAO {
	Boolean runApp(String streamImplementation, String inputStreamName, String outputStreamName);

	List<String> getApplicationsRunning();

	void stopApp(String streamName);
	
	void sendEvent(String streamName, Object[] event);
}

