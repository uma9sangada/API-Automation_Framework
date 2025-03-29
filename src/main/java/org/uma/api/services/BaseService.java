package org.uma.api.services;

import static io.restassured.RestAssured.given;
import org.uma.api.utils.LoggingFilterUtil;
import org.uma.api.utils.PropertiesUtil;
import org.uma.api.utils.ReportManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class BaseService {

	protected RequestSpecification requestSpecification;

	
	 static {
	        RestAssured.filters(new LoggingFilterUtil());
	    }
	
	public BaseService() {

		String baseUrl = PropertiesUtil.readProperty("BASE_URL");
		if (baseUrl == null || baseUrl.isEmpty()) {
			throw new IllegalArgumentException("BASE_URL property not found or is empty in properties file.");
		}
		requestSpecification = given().baseUri(baseUrl);
	}

	protected void setAuthToken(String token) {
		requestSpecification = requestSpecification.header("Authorization", "Bearer " + token);
	}

	protected Response postRequest(Object payload, String endpoint) {

		requestSpecification = requestSpecification.contentType(ContentType.JSON).body(payload);
		Response response = requestSpecification.post(endpoint);
		printRequestLogInReport(requestSpecification);
		printResponseLogInReport(response);
		return response;
	}

	protected Response putRequest(Object payload, String endpoint) {
		requestSpecification = requestSpecification.contentType(ContentType.JSON).body(payload);
		Response response = requestSpecification.put(endpoint);
		printRequestLogInReport(requestSpecification);
		printResponseLogInReport(response);
		return response;

	}

	protected Response getRequest(String endpoint) {

		Response response = requestSpecification.get(endpoint);
		printRequestLogInReport(requestSpecification);
		printResponseLogInReport(response);
		return response;

	}

	protected Response postRequest(String baseUrl, Object payload, String endpoint) {
		return RestAssured.given().baseUri(baseUrl).body(payload).post(endpoint);
	}

	private static void printRequestLogInReport(RequestSpecification requestSpecification) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
		ReportManager.logInfoDetails("Endpoint : " + queryableRequestSpecification.getBaseUri());
		ReportManager.logInfoDetails("Method : " + queryableRequestSpecification.getMethod());
		ReportManager.logInfoDetails("Headers :");
		ReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
		ReportManager.logInfoDetails("Request body : ");
		ReportManager.logJson(queryableRequestSpecification.getBody());

	}

	private static void printResponseLogInReport(Response response) {
		ReportManager.logInfoDetails("Response status : " + response.getStatusCode());
		ReportManager.logInfoDetails("Response Headers : ");
		ReportManager.logHeaders(response.getHeaders().asList());
		ReportManager.logInfoDetails("Response body : ");
		ReportManager.logJson(response.getBody().prettyPrint());
	}
}