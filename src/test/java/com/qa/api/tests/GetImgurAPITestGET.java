package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.restClient.RestClient;
import com.qa.api.util.Token;

import io.restassured.response.Response;



public class GetImgurAPITestGET {
	
	Map<Object,Object> tokenMap;
	String accessToken;
	String accountUserName;
	String refreshToken;
	

	@BeforeMethod
	public void setUp() {
		tokenMap=Token.getAccessToken();
		accessToken=tokenMap.get("access_token").toString();
		accountUserName=tokenMap.get("account_username").toString();
		refreshToken=tokenMap.get("refresh_token").toString();
		
	}
	
	
	@Test
	public void getAccountBlockStatusTest() {
		Map<String,String> authTokenMap=Token.getAuthToken();
		
		Response response=RestClient.doGet(null, "https://api.imgur.com", "/account/v1/"+accountUserName+"/block", authTokenMap, null, true);
	
		System.out.println(response.getStatusCode());;
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void getAccountImagesTest() {
		
		Map<String, String> tokenMap=Token.getClientId();
		Response response=RestClient.doGet(null, "https://api.imgur.com", "/3/account/me/images", tokenMap, null, true);
		
		System.out.println(response.getStatusCode());;
		System.out.println(response.prettyPrint());
	
	
	}
	
	
	// For image upload- content type will always be "multipart"
	@Test
	public void uploadImagePostAPITest() {
		Map<String,String> clientIdMap=Token.getClientId();
		
		Map<String,String> formMap = new HashMap<String, String>();
		formMap.put("title", "test title");
		formMap.put("description", "test descrition");
		Response response=RestClient.doPost("multipart", "https://api.imgur.com", "/3/upload", clientIdMap, null, true, formMap);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
}
