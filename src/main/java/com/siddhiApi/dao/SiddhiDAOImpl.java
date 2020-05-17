package com.siddhiApi.dao;


import com.siddhiApi.SiddhiApplicationManager.SiddhiApplicationManager;
import com.siddhiApi.entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiddhiDAOImpl implements SiddhiDAO{

	public String runApp(String streamImplementation, String inputStreamName, String outputStreamName) {
		// TODO Auto-generated method stub
		boolean successfulRun = SiddhiApplicationManager.runApp(streamImplementation, inputStreamName, outputStreamName);
		return successfulRun ? inputStreamName : "Run failed";
	}

	@Override
	public List<String> getApplicationsRunning() {
		return SiddhiApplicationManager.applications();
	}

	public void stopApp(String streamName) {
		// TODO Auto-generated method stub
		SiddhiApplicationManager.stopApp(streamName);
	}


	public void sendEvent(String streamName, Event event) {
		// TODO Auto-generated method stub
		SiddhiApplicationManager.sendEvent(streamName, event.parseToObject());
	}
}
