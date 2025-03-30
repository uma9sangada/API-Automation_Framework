package org.uma.api.practice.concepts;

//Required to import these
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

import org.joda.time.Seconds;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CookiesAndHeaders {

	// validate the cookie but fails because they change
	@Test
	void validateCookie() {
		when().get("https://google.com/").then().statusCode(anyOf (equalTo(200),equalTo(201))).
		cookie("AEC", notNullValue()).time(lessThan(2000L)).
		log().all();

	}

	// get single value cookie
	@Test
	void cookie() {
		Response resp = when().get("https://google.com/");
		String value = resp.getCookie("AEC");
		System.out.println(value);

	}

	// get all cookie values
	@Test(priority = 2)
	void cookies() {

		Response resp = when().get("https://google.com/");
		Map<String, String> values = resp.getCookies();
		System.out.println(values.keySet());

		for (String key : values.keySet()) {
			String value = resp.getCookie(key);
			System.out.println(key + ":" + value);
		}
	}

	
	////////headers
	@Test
	void validateHeader() {
		when().get("https://google.com/").then().header("Content-Encoding", "gzip").and().header("Server", "gws").and()
				.header("Content-Type", containsString("text/html;")).log().headers();//log only headers

	}

	// get single value cookie
	@Test
	void header() {
		Response resp = when().get("https://google.com/");
		String value = resp.getHeader("Server");
		System.out.println(value);

	}

	// get all cookie values
	@Test(priority = 2)
	void headers() {

		Response resp = when().get("https://google.com/");
		Headers myheaders = resp.getHeaders();

		for (Header header : myheaders) {

			System.out.println(header.getName() + ":" + header.getValue());
		}
	}
	
	
	
	///////logs
	@Test
	void logBody() {
		when().get("https://google.com/").then().log().body();
		when().get("https://google.com/").then().log().cookies();
		when().get("https://google.com/").then().log().headers();
	}
	
}
