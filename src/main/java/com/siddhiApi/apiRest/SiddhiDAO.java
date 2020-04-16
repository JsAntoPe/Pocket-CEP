package com.siddhiApi.apiRest;

import com.siddhiApi.entity.Event;

public interface SiddhiDAO {
	public String runApp(String file, String nameApp);
	
	public void stopApp(String name);
	
	public void sendEvent(String nameOfApp, Event event);
}

