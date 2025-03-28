package org.uma.api.services;

import static io.restassured.RestAssured.*;

import org.uma.api.utils.LoggingFilterUtil;
import org.uma.api.utils.PropertiesUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
        requestSpecification = RestAssured.given().baseUri(baseUrl).contentType(ContentType.JSON);
    }

    protected void setAuthToken(String token) {
        requestSpecification = requestSpecification.header("Authorization", "Bearer " + token);
    }

    protected Response postRequest(Object payload, String endpoint) {
        return requestSpecification.body(payload).post(endpoint);
    }

    protected Response putRequest(Object payload, String endpoint) {
        return requestSpecification.body(payload).put(endpoint);
    }

    protected Response getRequest(String endpoint) {
        return requestSpecification.get(endpoint);
    }

    protected Response postRequest(String baseUrl, Object payload, String endpoint) {
        return RestAssured.given().baseUri(baseUrl).contentType(ContentType.JSON).body(payload).post(endpoint);
    }
}