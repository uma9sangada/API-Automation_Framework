package org.uma.api.practice.concepts;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

	@Test
	void parameters() {
		// https://reqres.in/api/users?page=2&id=5

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/api").setBasePath("/users")
				.addQueryParam("page", 2).addQueryParam("id", 5).setContentType(ContentType.JSON).build();

		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();

		given().spec(req)

				.when().get().then().spec(res).log().all();

//
//		given().pathParam("path", "users").queryParam("page", 2).queryParam("id", 5)
//		//.header("Content-Type", "application/json")
//		.contentType(ContentType.JSON)
//				.when().get("https://reqres.in/api/{path}")
//
//				.then().statusCode(200).log().all();

	}

}
