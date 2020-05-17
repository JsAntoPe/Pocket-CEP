package com.siddhiApi.controller;

import com.siddhiApi.services.applicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.siddhiApi.dao.SiddhiDAO;
import com.siddhiApi.entity.Application;
import com.siddhiApi.entity.Event;

import java.util.List;

@RestController
@RequestMapping(value = "/api/applications")
public class SiddhiAPIController {

	Logger logger = LoggerFactory.getLogger(SiddhiAPIController.class);

	@Autowired
	private final applicationService applicationService;

	public SiddhiAPIController(applicationService applicationService) {
		this.applicationService = applicationService;
	}

	@GetMapping("/hello")
	public String hello(){
		String s = "Hello world";
		return s;
	}

	@PostMapping("/run")
	public String runApp(@RequestBody Application application) { //HttpEntity<String> instead of String
		logger.debug("Llego a run");
		logger.info(application.getApplicationCode());
		String AppName = applicationService.runApp(application.getApplicationCode(), application.getInputStreamName(), application.getOutputStreamName());
		return "The app with name " + AppName + " has been initialized";
	}

	@GetMapping("/streamsRunning")
	public String getStreamsRunning(){
		List<String> appsRunning;
		appsRunning = applicationService.getApplicationsRunning();
		if (appsRunning.size() > 0)
			return appsRunning.stream().reduce("Apps running: ", (s1, s2) -> s1 + "\n" + s2);
		else
			return "No applications running at the moment.";
	}
	
	@PostMapping("/sendEvent/{nameApp}")
	public void sendEvent(@PathVariable String nameApp, @RequestBody Event event) {
		//logger.info(Arrays.toString(event));
		applicationService.sendEvent(nameApp, event);
	}

	/*@PostMapping("/secondSendEvent")
	public void secondSendEvent(@RequestBody Event event){
		logger.info("El evento es: " + event.toString());
		logger.info("El array resultante es: ");
		for (Object element: event.secondParser()){
			logger.info("Elemento: " + element);
		}
	}*/
}
