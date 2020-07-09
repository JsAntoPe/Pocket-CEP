package com.siddhiApi.dao;


import com.siddhiApi.exceptions.DuplicatedEntity;
import com.siddhiApi.inMemoryStorage.PatternsDatabase;
import com.siddhiApi.siddhiApplicationManager.SiddhiApplicationManager;
import com.siddhiApi.entity.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatternDAOImpl implements PatternDAO {

	Logger logger = LoggerFactory.getLogger(PatternDAOImpl.class);

	private PatternsDatabase patternsDatabase = PatternsDatabase.getPatternsDatabase();

	public void runPattern(Pattern pattern) throws DuplicatedEntity {
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
	public List<Pattern> getPatterns() {
		List<Pattern> list = new ArrayList<>();
		patternsDatabase.getPatterns().addAll(list);
		return list;
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
