package com.siddhiApi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandlerJsonToObjectArray {
	
	@SuppressWarnings("serial")
	Map<String, Function<String, Object>> parsers = new HashMap<String, Function<String, Object>>() {
		{
			put("f", (s) -> Float.parseFloat(s));
			put("L", (s) -> Long.parseLong(s));
		}
	};
	
	
	
	public Object[] jsonToObjectArray(String event) {
		List<String> valuesArray = extractValuesFromJsonString(event);
		Object[] objectArray = new Object[valuesArray.size()];
		for(int i=0; i<valuesArray.size(); ++i) {
			System.out.println(valuesArray.size());
		}
		return objectArray;
		
	}
	
	private List<String> extractValuesFromJsonString(String event){
		int begin, end;
		List<String> jsonValuesForStream = new ArrayList<>();
		Pattern pattern = Pattern.compile(":(\"?\\w*\"?[^,{}]*)");
		Matcher matcher = pattern.matcher(event);
		while(matcher.find()) {
			begin = matcher.start();
			end = matcher.end();
			jsonValuesForStream.add(event.substring(begin, end));
		}
		return jsonValuesForStream;
	}
}
