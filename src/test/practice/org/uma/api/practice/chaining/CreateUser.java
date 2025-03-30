package org.uma.api.practice.chaining;


import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertNotNull;
import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateUser {

	@Test
	void test_createUser(ITestContext context) {
		String Token = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
		int id = 0;

		Faker fake = new Faker();
		JSONObject obj = new JSONObject();
		obj.put("name", fake.name().fullName());
		obj.put("gender", "Male");
		obj.put("email", fake.internet().emailAddress());
		obj.put("status", "inActive");

		RestAssured.baseURI = "https://gorest.co.in";

		Response res = given()
				.basePath("/public/v2/users")
				.headers("Authorization", "Bearer " + Token)
				.contentType("Application/json").body(obj.toString()).when()
				.post()
				.then().statusCode(201)
				.extract()
				.response();

		id = res.jsonPath().getInt("id");

		context.getSuite().setAttribute("bearerToken", Token); // suite level data passing
		context.setAttribute("bearerToken", Token); // test level data passing
		context.getSuite().setAttribute("user_id", id);
	}

}
