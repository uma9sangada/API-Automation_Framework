package org.uma.api.practice.chaining;

import static io.restassured.RestAssured.given;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class GetUser {

	@Test
	void test_GetUser(ITestContext context) {
		RestAssured.baseURI = "https://gorest.co.in";

		String token = (String) context.getSuite().getAttribute("bearerToken");
		int id = (Integer) context.getSuite().getAttribute("user_id");

		given().headers("Authorization", "Bearer " + token).contentType("Application/json").pathParam("Id", id)
				.basePath("/public/v2/users").when().get("/{Id}").then().statusCode(200).log().body();

	}

}
