package org.uma.api.practice.concepts;

//Required to import these
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GivenPrerequisites {

	@Test
	void parameters() {
		// https://reqres.in/api/users?page=2&id=5

		given().pathParam("path", "users").queryParam("page", 2).queryParam("id", 5)
		//.header("Content-Type", "application/json")
		.contentType(ContentType.JSON)
				.when()
				.get("https://reqres.in/api/{path}")

				.then()
				.
				statusCode(200).time(lessThan(2000L))			
				.log().all();

	}
	
	
	
	

}
