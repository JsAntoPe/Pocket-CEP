package com.siddhiApi.entity;

import com.siddhiApi.util.HandlerJsonToObjectArray;

public class Event {
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
		return objectArray;
	}
	
}
