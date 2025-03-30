package org.uma.api.practice.concepts;

import org.testng.annotations.Test;

//Required to import these
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

public class HTTPRequestMethods {

	int id;

	@Test(priority = 1)
	void getUsers() {

		when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log().all();

	}

	@Test(priority = 2)
	void createUsers() {

		HashMap<String, String> data = new HashMap<>();
		data.put("name", "morpheus");
		data.put("job", "leader");
		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");
//				.then().statusCode(201)	.log().all();	
	}

	@Test(priority = 3, dependsOnMethods = { "createUsers" })
	void updateUsers() {

		HashMap<String, String> data = new HashMap<>();
		data.put("name", "uma");
		data.put("job", "lead");
		given().contentType("application/json").body(data).when().put("https://reqres.in/api/users/"+id).then()
				.statusCode(200).log().all();
	}
	
	@Test(priority=4)
	void deleteUser()
	{
		when().delete("https://reqres.in/api/users/"+id).then()
		.statusCode(204).log().all();}

}
