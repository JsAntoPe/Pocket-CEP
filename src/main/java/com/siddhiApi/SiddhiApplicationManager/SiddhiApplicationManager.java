package com.siddhiApi.SiddhiApplicationManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiddhiApplicationManager {
	private static Map<String, RunSiddhiApplication> applications = new HashMap<String, RunSiddhiApplication>();
	
	public static boolean runApp(String file, String fileName) {
		//String fileName = file.substring(file.lastIndexOf("\\") + 1, file.indexOf("."));
		if(!applications.containsKey(fileName)){
			applications.put(fileName, new RunSiddhiApplication());
			applications.get(fileName).runApp(file, fileName);
			return true;
		}
		else
			return false;
	}
	
	public static void stopApp(String app) {
		applications.get(app).stopApp();
		applications.remove(app);
	}
	
	public static List<String> applications() {
		List<String> appsNames = new ArrayList<String>();
		for(String name:applications.keySet())
			appsNames.add(name);
		
		return appsNames;
	}
	
	public static void sendEvent(Object[] event) {
		System.out.println(event);
	}
}