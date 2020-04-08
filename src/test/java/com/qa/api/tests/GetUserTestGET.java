package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.restClient.RestClient;

import io.restassured.response.Response;

public class GetUserTestGET {

	String baseURI="https://gorest.co.in";
	String basePath="/public-api/users";
	String token="CRhUsB5k-OQ2EvgcPImU_8BLXyIytpC3Rywj";
	
	
	@Test
	public void getAllUserListAPITest() {
		Map<String,String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		Response response=RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		
		System.out.println("Pretty Print: "+response.prettyPrint());
		System.out.println("-----");
		System.out.println("-----");
		System.out.println("Status Code "+response.getStatusCode());
		
		
	}
	
	@Test
	public void getUserWithQueryParams() {
		Map<String,String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		Map<String,String> paramMaps = new HashMap<String,String>();
		paramMaps.put("first_name", "Vida");
		paramMaps.put("last_name", "Hamill");
		Response response=RestClient.doGet("JSON", baseURI, basePath, authTokenMap, paramMaps, true);
		
		System.out.println("Pretty Print: "+response.prettyPrint());
		System.out.println("Status Code: "+response.statusCode());
		
	}
}
