package org.uma.api.practice.chaining;


import static io.restassured.RestAssured.given;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class DeleteUser {

	@Test
	void test_DeleteUser(ITestContext context) {
		RestAssured.baseURI = "https://gorest.co.in";

		String token = (String) context.getSuite().getAttribute("bearerToken");
		int id = (Integer) context.getSuite().getAttribute("user_id");

		given().headers("Authorization", "Bearer " + token).contentType("Application/json").pathParam("Id", id)
				.basePath("/public/v2/users").when().delete("/{Id}").then().statusCode(204).log().body();

	}
}
