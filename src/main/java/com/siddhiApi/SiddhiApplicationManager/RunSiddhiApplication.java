package com.siddhiApi.SiddhiApplicationManager;

import com.siddhiApi.entity.Event;
import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.query.output.callback.QueryCallback;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.stream.output.StreamCallback;
import io.siddhi.core.util.EventPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.rmi.runtime.Log;

public class RunSiddhiApplication {
	static Logger logger = LoggerFactory.getLogger(RunSiddhiApplication.class);

	private static SiddhiManager siddhiManager = new SiddhiManager();
	private SiddhiAppRuntime siddhiAppRuntime;
	InputHandler inputHandler;

	public void runApp(String streamImplementation, String inputStreamName, String outputStreamName) {
		//Siddhi Application
		String siddhiApp = streamImplementation;

		siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiApp);

		//Start event processing
		siddhiAppRuntime.start();

		siddhiAppRuntime.addCallback(outputStreamName, new StreamCallback() {
			@Override
			public void receive(io.siddhi.core.event.Event[] events) {
				for (io.siddhi.core.event.Event event: events) {
					logger.info("Llego");
					logger.info("Evento de salida: " + event.getData(0).toString());
				}
			}
		});

		inputHandler = siddhiAppRuntime.getInputHandler(inputStreamName);
		logger.info("App running");
	}

	public void stopApp() {
		siddhiAppRuntime.shutdown();
	}

	public void sendEvent(Object[] event) {
		for(Object object: event){
			logger.info("Clase del objeto: " + object.getClass().toString());
		}
		try {
			inputHandler.send(event);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
