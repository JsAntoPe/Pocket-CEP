package com.siddhi.applicationController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhiApi.apiRest.SiddhiDAO;
import com.siddhiApi.entity.Application;
import com.siddhiApi.entity.Event;

@RestController
@RequestMapping("/api/application")
public class SiddhiApplicationManagerController {
	
	@Autowired
	private SiddhiDAO siddhiDao;
	
	
	@GetMapping("run")
	public String runApp(@RequestBody Application application) { //HttpEntity<String> instead of String
		//String json = httpEntity.getBody();
		return siddhiDao.runApp(application.getName(), application.getApplicationCode());
	}
	
	@GetMapping("/sendEvent/{nameApp}")
	public void sendEvent(@PathVariable String nameApp, @RequestBody Event event) {
		siddhiDao.sendEvent(nameApp, event);
	}
}
