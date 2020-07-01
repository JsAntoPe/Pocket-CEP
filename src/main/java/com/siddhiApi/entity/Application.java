package com.siddhiApi.entity;

import java.util.List;

public class Application {
	private String applicationName, outputStreamName, applicationCode;
	private List<String> inputStreamNames;

	public Application(String applicationName, List<String> inputStreamNames, String outputStreamName, String applicationCode) {
		this.applicationName = applicationName;
		this.inputStreamNames = inputStreamNames;
		this.outputStreamName = outputStreamName;
		this.applicationCode = applicationCode;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
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

	public List<String> getInputStreamNames() {
		return inputStreamNames;
	}

	public void setInputStreamNames(List<String> inputStreamNames) {
		this.inputStreamNames = inputStreamNames;
	}
}
