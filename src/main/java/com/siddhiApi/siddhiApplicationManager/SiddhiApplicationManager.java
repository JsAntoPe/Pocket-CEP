package com.siddhiApi.siddhiApplicationManager;

import com.siddhiApi.exceptions.DuplicatedEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiddhiApplicationManager {
	static Logger logger = LoggerFactory.getLogger(SiddhiApplicationManager.class);
	private static final Map<String, RunSiddhiApplication> applications = new HashMap<String, RunSiddhiApplication>();
	private static final Map<String, List<String>> inputStreamInApplication = new HashMap<>();

	public static void runApp(String applicationName, String[] inputStreamNames, String outputStreamName, String streamImplementation) throws DuplicatedEntity {
		//String fileName = file.substring(file.lastIndexOf("\\") + 1, file.indexOf("."));
		logger.info("applicationName: " + applicationName);
		if(applications.containsKey(applicationName)){
			logger.info("applicationName already appears: " + applicationName);
			throw new DuplicatedEntity("A pattern with that name already exists.");
		}
		applications.put(applicationName, new RunSiddhiApplication());
		applications.get(applicationName).runApp(inputStreamNames, outputStreamName, streamImplementation);
		for(String inputStreamName: inputStreamNames){
			addApplicationToInputStream(inputStreamName, applicationName);
		}
	}

	//TODO Refactor this function, so it does not take n power n time. And it can do it in n.
	public static void stopApp(String app) {
		applications.get(app).stopApp();
		applications.remove(app);
		for (String key: inputStreamInApplication.keySet()){
			if(inputStreamInApplication.get(key).contains(app)){
				removeApplicationFromInputStream(key, app);
			}
		}
	}
	
	public static List<String> applications() {
		List<String> appsNames = new ArrayList<String>();
		for(String name: applications.keySet())
			appsNames.add(name);
		
		return appsNames;
	}
	
	public static void sendEvent(String streamName, Object[] event) {
		List<String> applicationsListContainsStream = inputStreamInApplication.get(streamName);
		if(applicationsListContainsStream != null){
			for (String application:applicationsListContainsStream){
				applications.get(application).sendEvent(streamName, event);
			}
		}
	}

	private static void addApplicationToInputStream(String inputStreamName, String applicationName){
		if (!inputStreamInApplication.containsKey(inputStreamName)){
			inputStreamInApplication.put(inputStreamName, new ArrayList<>());
		}
		inputStreamInApplication.get(inputStreamName).add(applicationName);
	}

	private static void removeApplicationFromInputStream(String inputStreamName, String applicationName){
		inputStreamInApplication.get(inputStreamName).remove(applicationName);
		if (inputStreamInApplication.get(inputStreamName).size() == 0){
			inputStreamInApplication.remove(inputStreamName);
		}

	}
}