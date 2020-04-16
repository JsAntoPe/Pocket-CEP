package com.siddhiApi.SiddhiApplicationManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.wso2.siddhi.core.SiddhiAppRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.stream.output.StreamCallback;
import org.wso2.siddhi.core.util.EventPrinter;

public class RunSiddhiApplication {
	private static SiddhiManager siddhiManager = new SiddhiManager();
	private SiddhiAppRuntime siddhiAppRuntime;
	InputHandler inputHandler;
	public void runApp(String file, String fileName) {
		//Siddhi Application
		try {
			String siddhiApp = new String(Files.readAllBytes(Paths.get(file)));
			
			siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiApp);

	        //Start event processing
	        siddhiAppRuntime.start();
	        
	        siddhiAppRuntime.addCallback(fileName + "stream", new StreamCallback() {
	            @Override
	            public void receive(Event[] events) {
	                EventPrinter.print(events);
	            }
	        });
	        inputHandler = siddhiAppRuntime.getInputHandler(fileName + "stream");
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		/*} catch(org.wso2.siddhi.query.api.exception.AttributeNotExistException e) {
			e.printStackTrace();
		} catch(org.wso2.siddhi.query.api.exception.DuplicateAttributeException e) {
			e.printStackTrace();
		} catch(org.wso2.siddhi.query.api.exception.UnsupportedAttributeTypeException e) {
			e.printStackTrace();*/
		}
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
