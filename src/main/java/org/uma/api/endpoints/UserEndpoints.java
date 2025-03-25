package org.uma.api.endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserEndpoints {


	 public static Response createUser(RequestSpecification requestSpec, String payload) {
	        return given().spec(requestSpec).body(payload).post("/users");
	    }

	 public static Response getUser(RequestSpecification requestSpec, int userId) {
		    return given().spec(requestSpec).get("/users/" + userId); // Adjust URL if needed
		}

	    public static Response updateUser(RequestSpecification requestSpec, int userId, String payload) {
	        return given().spec(requestSpec).body(payload).put("/users/" + userId);
	    }

	    public static Response deleteUser(RequestSpecification requestSpec, int userId) {
	        return given().spec(requestSpec).delete("/users/" + userId);
	    }

	    public static Response getAllUsers(RequestSpecification requestSpec) {
	        return given().spec(requestSpec).get("/users");
	    }
}
