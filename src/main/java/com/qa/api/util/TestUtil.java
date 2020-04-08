package com.qa.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	
	/**
	 * This method is used to convert pojoobject to a jsonString
	 * @param obj
	 * @return jsonString
	 */
	
	public static String getSerializedJSON(Object obj) {
		
		ObjectMapper objMap = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString=objMap.writeValueAsString(obj);
			System.out.println("Json output: "+jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonString;
	}

}
