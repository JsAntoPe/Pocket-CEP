package com.siddhiApi.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.siddhiApi.SiddhiApplicationManager.RunSiddhiApplication;
import com.siddhiApi.util.HandlerJsonToObjectArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*public class Event {
	private Map<String, Object> attributes = new LinkedHashMap<>();

	@JsonAnySetter
	void setAttributes(String key, Object value){
		attributes.put(key, value);
	}
}*/
public class Event {
	Logger logger = LoggerFactory.getLogger(RunSiddhiApplication.class);

	private String event;
	
	public Event(String event) {
		this.event = event;
	}
	
	public String getEvent() {
		return event;
	}
	
	public Object[] parseToObject() { //:{1}("?\w*"?[^,{}]*)
		HandlerJsonToObjectArray handler = new HandlerJsonToObjectArray();
		Object[] objectArray = handler.jsonToObjectArray(getEvent());
		for (Object object: objectArray){
			logger.info("Objeto del array: " + object.toString());
		}
		return objectArray;
	}
	
}
