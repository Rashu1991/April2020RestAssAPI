package com.qa.api.restClient;

import java.io.File;
import java.util.Map;

import com.qa.api.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	// Http Methods: GET POST DELETE PUT
	static RequestSpecification request;

	// GET
	public static Response doGet(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> paramMaps, boolean log) {

		if(setBaseURI(baseURI)) {
		request = createRequest(contentType, token, paramMaps, log);
		return getResponse(request, "GET", basePath);
		}
		return null;
	}
	

	// POST
	public static Response doPost(String contentType,String baseURI,String basePath,
			Map<String,String> token,Map<String,String> paramMaps,boolean log, Object obj) {
		
		if(setBaseURI(baseURI)) {
			request=createRequest(contentType, token, paramMaps, log);
			addRequestPayload(request,obj);
			return getResponse(request, "POST", basePath);
		}
		return null;	
	}
	
	public static void addRequestPayload(RequestSpecification request,Object obj) {
		
		if(obj instanceof Map) {
			request.formParams((Map<String, String>) obj);
		}
		else {
		String jsonBody=TestUtil.getSerializedJSON(obj);
		request.body(jsonBody);
	}
	}
	
	public static boolean setBaseURI(String baseURI) {
		
		if(baseURI==null || baseURI.isEmpty()) {
			System.out.println("Please pass correct Uri");
			return false;
		}
		try {
		RestAssured.baseURI = baseURI;
		return true;
		}
		catch(Exception e) {
			System.out.println("some exception got occured while assigning the base URI with Rest Assured");
			return false;
		}
	}

	public static RequestSpecification createRequest(String contentType, Map<String,String> token, Map<String, String> paramMaps,
			boolean log) {

		if (log) {
			request = RestAssured.given().log().all();

		} else {
			request = RestAssured.given();
		}

		if (token.size()>0) {
			//request.header("Authorization", "Bearer " + token);
			request.headers(token);
		}

		if (!(paramMaps == null)) {
			request.queryParams(paramMaps);
		}

		if(contentType !=null) {
		if (contentType.equalsIgnoreCase("JSON")) {
			request.contentType(ContentType.JSON);
		} else if (contentType.equalsIgnoreCase("XML")) {
			request.contentType(ContentType.XML);
		} else if (contentType.equalsIgnoreCase("TEXT")) {
			request.contentType(ContentType.TEXT);
		}	else if (contentType.equalsIgnoreCase("multipart")) {
			request.multiPart("image", new File("C:\\Users\\Mudit\\Desktop\\favicon.jpg"));
		}
		
		}
		return request;
	}

	public static Response getResponse(RequestSpecification request, String httpMethod, String basePath) {
		return executeAPI(request, httpMethod, basePath);
	}

	public static Response executeAPI(RequestSpecification request, String httpMethod, String basePath) {

		Response response = null;

		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		default:
			System.out.println("Please pass the correct http method");
			break;
		}

		return response;
	}
}
