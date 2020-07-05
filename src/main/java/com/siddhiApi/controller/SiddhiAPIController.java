package com.siddhiApi.controller;

import com.siddhiApi.entity.Application;
import com.siddhiApi.services.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1/applications")
public class SiddhiAPIController {

	Logger logger = LoggerFactory.getLogger(SiddhiAPIController.class);

	@Autowired
	private final ApplicationService applicationService;

	public SiddhiAPIController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/run")
	public void runApp(@RequestBody Application application) { //HttpEntity<String> instead of String
		//try {
		try {
			applicationService.runApp(application);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//} /*catch (Exception e) {
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An application with that name already exists.", e);
		//}*/
	}

	/*@GetMapping("/streamsRunning")
	public String getStreamsRunning(){
		List<String> appsRunning;
		appsRunning = applicationService.getApplicationsRunning();
		if (appsRunning.size() > 0)
			return appsRunning.stream().reduce("Apps running: ", (s1, s2) -> s1 + "\n" + s2);
		else
			return "No applications running at the moment.";
	}*/
	
	/*@PostMapping("/sendEvent/{nameApp}")
	public void sendEvent(@PathVariable String nameApp, @RequestBody Event event) {
		//logger.info(Arrays.toString(event));
		applicationService.sendEvent(nameApp, event);
	}*/

	/*@PostMapping("/secondSendEvent/{streamName}")
	public void secondSendEvent(@PathVariable String streamName, @RequestBody CustomEvent event){
		try {
			applicationService.sendEvent(streamName, event);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("There was a exception: " + e);
		}
	}*/
}
