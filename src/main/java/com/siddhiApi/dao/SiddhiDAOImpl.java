package com.siddhiApi.dao;


import com.siddhiApi.SiddhiApplicationManager.SiddhiApplicationManager;
import com.siddhiApi.entity.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiddhiDAOImpl implements SiddhiDAO{

	Logger logger = LoggerFactory.getLogger(SiddhiDAOImpl.class);

	public void runApp(Application application) throws Exception {
		// TODO Auto-generated method stub
		SiddhiApplicationManager.runApp(
				application.getApplicationName(),
				application.getInputStreamNames(),
				application.getOutputStreamName(),
				application.getApplicationCode()
		);
		logger.info("Application Name: " + application.getApplicationName());
		for (String inputStream: application.getInputStreamNames()){
			logger.info("Application Input Stream Name: " + inputStream);
		}
		logger.info("Application Output Stream: " + application.getOutputStreamName());
		logger.info("Application Code: " + application.getApplicationCode());

	}

	@Override
	public List<String> getApplicationsRunning() {
		return SiddhiApplicationManager.applications();
	}

	public void stopApp(String app) {
		// TODO Auto-generated method stub
		SiddhiApplicationManager.stopApp(app);
	}


	public void sendEvent(String streamName, Object[] event) {
		// TODO Auto-generated method stub
		SiddhiApplicationManager.sendEvent(streamName, event);
	}
}
