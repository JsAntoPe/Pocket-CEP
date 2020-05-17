package com.siddhiApi.entity;

public class Application {
private String inputStreamName, outputStreamName, applicationCode;
	
	public Application(String inputStreamName, String outputStreamName, String applicationCode) {
		this.inputStreamName = inputStreamName;
		this.outputStreamName = outputStreamName;
		this.applicationCode = applicationCode;
	}

	public String getInputStreamName() {
		return inputStreamName;
	}

	public void setInputStreamName(String inputStreamName) {
		this.inputStreamName = inputStreamName;
	}

	public String getOutputStreamName() {
		return outputStreamName;
	}

	public void setOutputStreamName(String outputStreamName) {
		this.outputStreamName = outputStreamName;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
}
