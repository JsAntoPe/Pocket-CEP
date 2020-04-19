package com.siddhiApi.applicationController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.siddhiApi.apiRest.SiddhiDAO;
import com.siddhiApi.entity.Application;
import com.siddhiApi.entity.Event;

import java.util.List;

@RestController
@RequestMapping(value = "/api/applications")
public class SiddhiApplicationManagerController {

	Logger logger = LoggerFactory.getLogger(SiddhiApplicationManagerController.class);

	@Autowired
	private final SiddhiDAO siddhiDao;

	public SiddhiApplicationManagerController(SiddhiDAO siddhiDao) {
		this.siddhiDao = siddhiDao;
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
		String AppName = siddhiDao.runApp(application.getApplicationCode(), application.getStreamName());
		return "The app with name " + AppName + " has been initialized";
	}

	@GetMapping("/streamsRunning")
	public String getStreamsRunning(){
		List<String> appsRunning;
		appsRunning = siddhiDao.getApplicationsRunning();
		if (appsRunning.size() > 0)
			return appsRunning.stream().reduce("Apps running: ", (s1, s2) -> s1 + "\n" + s2);
		else
			return "No applications running at the moment.";
	}
	
	@PostMapping("/sendEvent/{nameApp}")
	public void sendEvent(@PathVariable String nameApp, @RequestBody Event event) {
		//logger.info(Arrays.toString(event));
		siddhiDao.sendEvent(nameApp, event);
	}
}
