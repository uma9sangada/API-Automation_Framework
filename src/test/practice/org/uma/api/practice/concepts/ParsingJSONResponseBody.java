package org.uma.api.practice.concepts;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseBody {

	// approach 1
	// @Test
	void validateBody() {
		given().contentType(ContentType.JSON).when().get("http://localhost:3000/students").then().statusCode(200)
				.header("Content-Type", "application/json").body("student[3].phone", equalTo("123456"));

	}

	// approach 2
	// @Test
	void validateBody2() {
		Response resp = given().contentType(ContentType.JSON).when().get("http://localhost:3000/Details");

		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertEquals(resp.header("Content-Type"), "application/json");
		System.out.println(resp.jsonPath().get("[0].id").toString());//
		String value = resp.
				jsonPath()
				.get("[0].id").toString();
		Assert.assertEquals(value, "2");

	}
	
	// approach 2
	@Test
	void validateBody3() {
		
		 double total=0;
		RestAssured.baseURI = "http://localhost:3000";
		Response resp = given().contentType(ContentType.JSON).when().get("/books");
 
		resp.then().assertThat().statusCode(200);

		String responseBody = resp.getBody().asString();
		System.out.println("Raw Response: " + responseBody);

		try {

			/*
			 * JSON Object ({...}): Represents a collection of key-value pairs. JSON Array
			 * ([...]): Represents an ordered list of values.
			 * 
			 * JSONArray booksArray = new JSONArray(responseBody); This line now parses the
			 * response directly into a JSONArray because the root of your JSON is an array.
			 * if not JSONObject js = new JSONObject(responseBody); JSONArray detailsArray =
			 * js.getJSONArray("Details");*/
			/*
JSON object
{
//1 JSON object
}
JSON array
[
//1 JSON array
]
JSON element- either be a JSON array or JSON object.

JSON object -> JSON Array -> JSON element */
			 
			
			//JSONObject obj = new JSONObject(responseBody);// if json starts with {}
			JSONArray detailsArray = new JSONArray(responseBody);// if json starts with []
			assertNotNull(detailsArray);

			System.out.println(detailsArray.length()); //obj.getJSONArray("book").length() //book is the array name

			for (int i = 0; i < detailsArray.length(); i++) {
				JSONObject detailObject = detailsArray.getJSONObject(i);
				String name = detailObject.getString("copies");
				assertNotNull(name);
				total+= Double.parseDouble(name);
				
			}
			System.out.println(total);
		} catch (JSONException e) {
			fail("Invalid JSON response: " + e.getMessage());
		}
	}

}
