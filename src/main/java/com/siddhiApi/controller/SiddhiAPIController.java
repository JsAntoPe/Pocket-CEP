package com.siddhiApi.controller;

import com.siddhiApi.entity.Pattern;
import com.siddhiApi.exceptions.NotFoundException;
import com.siddhiApi.exceptions.PropertyNotFoundOnSelect;
import com.siddhiApi.services.PatternService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1/patterns")
public class SiddhiAPIController {

	Logger logger = LoggerFactory.getLogger(SiddhiAPIController.class);

	@Autowired
	private final PatternService patternService;

	public SiddhiAPIController(PatternService patternService) {
		this.patternService = patternService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/run")
	public void runPattern(@RequestBody Pattern pattern) { //HttpEntity<String> instead of String
		//try {
		try {
			patternService.runPattern(pattern);
		} catch (NotFoundException nfe){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, nfe);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
		}
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
