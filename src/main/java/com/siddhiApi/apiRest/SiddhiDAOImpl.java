package com.siddhiApi.apiRest;


import com.siddhiApi.SiddhiApplicationManager.SiddhiApplicationManager;
import com.siddhiApi.entity.Event;

public class SiddhiDAOImpl implements SiddhiDAO{

	public String runApp(String file, String nameApp) {
		// TODO Auto-generated method stub
		boolean successfulRun = SiddhiApplicationManager.runApp(file, nameApp);
		return successfulRun ? nameApp : "Run failed";
	}


	public void stopApp(String name) {
		// TODO Auto-generated method stub
		SiddhiApplicationManager.stopApp(name);
	}


	public void sendEvent(String nameOfApp, Event event) {
		// TODO Auto-generated method stub
		SiddhiApplicationManager.sendEvent(event.parseToObject());
	}
	
}
