package com.siddhiApi.entity;

public class Application {
private String streamName, applicationCode;
	
	public Application(String streamName, String applicationCode) {
		this.streamName = streamName;
		this.applicationCode = applicationCode;
	}
	
	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String name) {
		this.streamName = name;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
}
