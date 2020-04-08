package com.qa.api.util;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;

public class Token {

	public static Map<Object,Object> appTokenMap;
	public static Map<String,String> tokenMap = new HashMap<String, String>();
	public static String clientId="3d2cb23e1d1ca6b";
	
	public static Map<Object,Object>  getAccessToken() {
		
		Map<String,String> formParams = new HashMap<String,String>();
		formParams.put("refresh_token", "f0937b02efe2ada89452a4e485ba2cf0c86eab30");
		formParams.put("client_id", "3d2cb23e1d1ca6b");
		formParams.put("client_secret", "2050ec698b62e5179cfc20acd4199980076c0df5");
		formParams.put("grant_type", "refresh_token");
		
		JsonPath tokenJson=
		given()
			.formParams(formParams)
		.when()
			.post("https://api.imgur.com/oauth2/token")
				.then()
					.extract().jsonPath();
		
		
		
		appTokenMap= tokenJson.getMap("");
		return appTokenMap;
			
	}
	
	public static Map<String,String> getAuthToken() {
		String authToken=appTokenMap.get("access_token").toString();
		System.out.println("Auth Token "+authToken);
		tokenMap.put("Authorization", "Bearer "+authToken);
		return tokenMap;
	}
	
	public static Map<String,String> getClientId() {
		System.out.println("Client ID --- "+clientId);
		tokenMap.put("Authorization", "Client-ID "+clientId);
		return tokenMap;
		
	}
	
}

