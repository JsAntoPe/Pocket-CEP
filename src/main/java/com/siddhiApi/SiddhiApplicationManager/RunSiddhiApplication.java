package com.siddhiApi.SiddhiApplicationManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.siddhi.core.SiddhiAppRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.stream.output.StreamCallback;
import org.wso2.siddhi.core.util.EventPrinter;

public class RunSiddhiApplication {
	Logger logger = LoggerFactory.getLogger(RunSiddhiApplication.class);

	private static SiddhiManager siddhiManager = new SiddhiManager();
	private SiddhiAppRuntime siddhiAppRuntime;
	InputHandler inputHandler;
	public void runApp(String streamImplementation, String streamName) {
		//Siddhi Application
			String siddhiApp = streamImplementation;
			
			siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiApp);

	        //Start event processing
	        siddhiAppRuntime.start();
	        
	        siddhiAppRuntime.addCallback(streamName, new StreamCallback() {
	            @Override
	            public void receive(Event[] events) {
	                EventPrinter.print(events);
	            }
	        });
	        inputHandler = siddhiAppRuntime.getInputHandler(streamName);
    		logger.info("App running");
	}
	
	public void stopApp() {
		siddhiAppRuntime.shutdown();
	}
	
	public void sendEvent(Object[] event) {
		try {
			inputHandler.send(event);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
