package com.siddhiApi.applicationController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.siddhiApi.apiRest.SiddhiDAO;
import com.siddhiApi.entity.Application;
import com.siddhiApi.entity.Event;

@RestController
@RequestMapping("/api/application")
public class SiddhiApplicationManagerController {

	Logger logger = LoggerFactory.getLogger(SiddhiApplicationManagerController.class);

	@Autowired
	private final SiddhiDAO siddhiDao;

	public SiddhiApplicationManagerController(SiddhiDAO siddhiDao) {
		this.siddhiDao = siddhiDao;
	}

	@GetMapping("/run")
	public String runApp(@RequestBody Application application) { //HttpEntity<String> instead of String
		logger.debug("Llego a run");
		String AppName = siddhiDao.runApp(application.getName(), application.getApplicationCode());
		//return "The app with name " + AppName + " has been initialized";
		AppName = "Hola mundo";
		return AppName;
	}
	
	@PostMapping("/sendEvent/{nameApp}")
	public void sendEvent(@PathVariable String nameApp, @RequestBody Event event) {
		siddhiDao.sendEvent(nameApp, event);
	}
}
