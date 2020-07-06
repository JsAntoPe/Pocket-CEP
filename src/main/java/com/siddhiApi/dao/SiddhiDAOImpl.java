package com.siddhiApi.dao;


import com.siddhiApi.siddhiApplicationManager.SiddhiApplicationManager;
import com.siddhiApi.entity.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiddhiDAOImpl implements SiddhiDAO{

	Logger logger = LoggerFactory.getLogger(SiddhiDAOImpl.class);

	public void runPattern(Pattern pattern) throws Exception {
		// TODO Auto-generated method stub
		SiddhiApplicationManager.runApp(
				pattern.getPatternName(),
				pattern.getInputStreamNames(),
				pattern.getOutputStreamName(),
				pattern.getPatternCode()
		);
		logger.info("Pattern Name: " + pattern.getPatternName());
		for (String inputStream: pattern.getInputStreamNames()){
			logger.info("Pattern Input Stream Name: " + inputStream);
		}
		logger.info("Pattern Output Stream: " + pattern.getOutputStreamName());
		logger.info("Pattern Code: " + pattern.getPatternCode());
	}

	@Override
	public List<String> getPatternsRunning() {
		return SiddhiApplicationManager.applications();
	}

	public void stopPattern(String patternName) {
		// TODO Auto-generated method stub
		SiddhiApplicationManager.stopApp(patternName);
	}


	public void sendEvent(String streamName, Object[] event) {
		// TODO Auto-generated method stub
		SiddhiApplicationManager.sendEvent(streamName, event);
	}
}
