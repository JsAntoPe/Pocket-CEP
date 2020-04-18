package com.siddhiApi.apiRest;

import com.siddhiApi.entity.Event;

public interface SiddhiDAO {
	String runApp(String file, String nameApp);
	
	void stopApp(String name);
	
	void sendEvent(String nameOfApp, Event event);
}

