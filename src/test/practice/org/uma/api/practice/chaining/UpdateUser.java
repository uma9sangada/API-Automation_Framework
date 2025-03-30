package org.uma.api.practice.chaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;

public class UpdateUser {

	@Test
	void test_UpdateUser(ITestContext context) {
		String token = (String) context.getSuite().getAttribute("bearerToken");
		int id = (Integer) context.getSuite().getAttribute("user_id");

		Faker fake = new Faker();
		JSONObject obj = new JSONObject();
		obj.put("name", fake.name().fullName());
		obj.put("gender", "Male");
		obj.put("email", fake.internet().emailAddress());
		obj.put("status", "inActive");

		RestAssured.baseURI = "https://gorest.co.in";

		given().basePath("/public/v2/users").headers("Authorization", "Bearer " + token).pathParam("Id", id)
				.contentType("Application/json").body(obj.toString()).when().put("/{Id}").then().statusCode(200).log()
				.body();
	}

}
