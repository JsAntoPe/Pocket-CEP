package com.siddhiApi.dao;

import com.siddhiApi.entity.Application;
import com.siddhiApi.entity.Event;

import java.util.List;


public interface SiddhiDAO {
	void runApp(Application application) throws Exception;

	List<String> getApplicationsRunning();

	void stopApp(String app);
	
	void sendEvent(String streamName, Object[] event);
}

