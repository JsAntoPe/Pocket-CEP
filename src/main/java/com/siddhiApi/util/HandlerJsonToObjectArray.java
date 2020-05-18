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
	Map<Character, Function<String, Object>> parsers = new HashMap<Character, Function<String, Object>>() {
		{
			put('f', (s) -> Float.parseFloat(s));
			put('L', (s) -> Long.parseLong(s));
			put('\"', (s) -> s.substring(1, s.length()));
		}
	};
	
	
	
	public Object[] jsonToObjectArray(String event) {
		List<String> valuesArray = extractValuesFromJsonString(event);
		Object[] objectArray = new Object[valuesArray.size()];
		for(int i=0; i<valuesArray.size(); ++i) {
			objectArray[i] = parsers.get(valuesArray.get(i).charAt(valuesArray.get(i).length() - 1))
					.apply(valuesArray.get(i).substring(0, valuesArray.get(i).length() - 1));
		}
		return objectArray;
		
	}
	
	private List<String> extractValuesFromJsonString(String event){
		int begin, end;
		List<String> jsonValuesForStream = new ArrayList<>();
		Pattern pattern = Pattern.compile(":(\"?\\w*\"?[^,{}]*)");
		Matcher matcher = pattern.matcher(event);
		while(matcher.find()) {
			begin = matcher.start() + 2;
			end = matcher.end();
			jsonValuesForStream.add(event.substring(begin, end));
		}
		return jsonValuesForStream;
	}
}
