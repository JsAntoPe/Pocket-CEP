package com.siddhiApi.entity;

public class Application {
private String name, applicationCode;
	
	public Application(String name, String applicationCode) {
		this.name = name;
		this.applicationCode = applicationCode;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
}
