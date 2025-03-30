package org.uma.api.practice.concepts;

//Required to import these
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Authorizations {
	
	/*Authentication - validating valid user or not
	 * Autherization - check access or permissions
	 * 
	 */
		@Test(priority=3)
		void basicAuth() {
			RestAssured.baseURI="https://postman-echo.com";
			
		Response resp=	given().auth().basic("postman", "password")
			.when().get("/basic-auth");
		
		resp.then().log().body().assertThat().statusCode(200).body("authenticated", equalTo(true));
			
		}
		
		@Test(priority=3)
		void digestAuth() {
			RestAssured.baseURI="https://postman-echo.com";
			
		Response resp=	given().auth().digest("postman", "password")
			.when().get("/basic-auth");
		
		resp.then().log().body().assertThat().statusCode(200).body("authenticated", equalTo(true));
			
		}

		
		@Test(priority=3)
		void preemptiveAuth() {
			RestAssured.baseURI="https://postman-echo.com";
			
		Response resp=	given().auth().preemptive().basic("postman", "password")
			.when().get("/basic-auth");
		
		resp.then().log().body().assertThat().statusCode(200).body("authenticated", equalTo(true));
			
		}

		
		@Test(priority=3)
		void bearerAuth() {
			RestAssured.baseURI="https://api.github.com";
			String token="wsedrftghjk";
		Response resp=	given().headers("Authorization", "Bearer"+token)
			.when().get("/users/repos");
		
		resp.then().log().body().assertThat().statusCode(200);
			
		}
		
		@Test(priority=3)
		void oAuth1() {
			RestAssured.baseURI="https://api.github.com";
			
		Response resp=	given().auth().oauth("consumerKey", "consumerSecret", "accesstoken", "tokensecret")
			.when().get("");
		
		resp.then().log().body().assertThat().statusCode(200);
			
		}

		

		@Test(priority=3)
		void oAuth2() {
			RestAssured.baseURI="https://api.github.com";
			
		Response resp=	given().auth().oauth2("token")
			.when().get("");
		
		resp.then().log().body().assertThat().statusCode(200);
			
		}
		
		@Test(priority=3)
		void apiKey() {

	        given()
	            .queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
	            .pathParam("mypath", "data/2.5/forecast/daily")
	            .queryParam("q", "Delhi")
	            .queryParam("units", "metric")
	            .queryParam("cnt", "7")
	        .when()
	            .get("https://api.openweathermap.org/{mypath}")
	        .then()
	            .statusCode(200)
	            .log().all();
	    }
		


}
