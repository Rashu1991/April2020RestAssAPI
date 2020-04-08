package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.pojo.User;
import com.qa.api.restClient.RestClient;
import com.qa.api.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTestPOST {

	String baseURI="https://gorest.co.in";
	String basePath="/public-api/users";
	String token="CRhUsB5k-OQ2EvgcPImU_8BLXyIytpC3Rywj";
	
	@DataProvider
	public Object[][] getUserData() {
		Object [][]userdata=ExcelUtil.getTestData("userdata");
		return userdata;
		
	}
	
	
	@Test(dataProvider="getUserData")
	public void createUserAPIPOSTTest(String firstname,String lastname,String gender, String dob
			,String email,String phoneNumber,String website,String address,String status) {
		
		Map<String,String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization","Bearer "+token);
		
		User user = new User(firstname,lastname, gender,  dob
				, email, phoneNumber, website, address, status);
		Response response=RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		
		System.out.println("Pretty Print: "+response.prettyPrint());
		System.out.println("-----");
		System.out.println("-----");
		System.out.println("Status Code "+response.getStatusCode());
		System.out.println("===========================");
		
	}
}
