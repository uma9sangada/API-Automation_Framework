package org.uma.api.utils;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;

public class CustomAssertions {

	public static void assertStatusCode(Response response, int expectedStatusCode) {
		if (response.getStatusCode() != expectedStatusCode) {
			throw new AssertionError(
					"Status code mismatch: Expected " + expectedStatusCode + ", but got " + response.getStatusCode());
		}
	}

	public static void assertContentType(Response response, String expectedContentType) {
		if (!response.getContentType().equals(expectedContentType)) {
			throw new AssertionError("Content type mismatch: Expected " + expectedContentType + ", but got "
					+ response.getContentType());
		}
	}

	public static void assertJsonResponseValue(Response response, String jsonPath, Object expectedValue) {
		response.then().assertThat().body(jsonPath, equalTo(expectedValue));
	}

	public static void assertJsonResponseValueContains(Response response, String jsonPath, String expectedValue) {
		response.then().assertThat().body(jsonPath, containsString(expectedValue));
	}

	public static void assertJsonResponseValueNotEmpty(Response response, String jsonPath) {
		response.then().assertThat().body(jsonPath, not(emptyOrNullString()));
	}

//	public static void assertJsonSchema(Response response, String schemaPath) {
//		response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
//	}

	public static void assertResponseTimeLessThan(Response response, long maxResponseTime) {
		response.then().assertThat().time(Matchers.lessThan(maxResponseTime));
	}

	public static void assertHeader(Response response, String headerName, String expectedValue) {
		if (!response.getHeader(headerName).equals(expectedValue)) {
			throw new AssertionError("Header mismatch for " + headerName + ": Expected " + expectedValue + ", but got "
					+ response.getHeader(headerName));
		}
	}

	public static void assertHeaderContains(Response response, String headerName, String expectedValue) {
		if (!response.getHeader(headerName).contains(expectedValue)) {
			throw new AssertionError("Header does not contain " + expectedValue + " for " + headerName
					+ ": Header value is: " + response.getHeader(headerName));
		}
	}

	public static void assertCookie(Response response, String cookieName, String expectedValue) {
		if (!response.getCookie(cookieName).equals(expectedValue)) {
			throw new AssertionError("Cookie mismatch for " + cookieName + ": Expected " + expectedValue + ", but got "
					+ response.getCookie(cookieName));
		}
	}

	public static void assertResponseBody(Response response, String expectedBody) {
		if (!response.getBody().asString().equals(expectedBody)) {
			throw new AssertionError(
					"Response body mismatch: Expected " + expectedBody + ", but got " + response.getBody().asString());
		}
	}

	public static void assertResponseBodyContains(Response response, String expectedBodyPart) {
		if (!response.getBody().asString().contains(expectedBodyPart)) {
			throw new AssertionError("Response body does not contain " + expectedBodyPart + ": Response body is: "
					+ response.getBody().asString());
		}
	}

	public static void assertJsonArraySize(Response response, String jsonPath, int expectedSize) {
		response.then().assertThat().body(jsonPath, hasSize(expectedSize));
	}

	public static void assertJsonArrayContains(Response response, String jsonPath, Object expectedElement) {
		response.then().assertThat().body(jsonPath, hasItem(expectedElement));
	}

	public static void assertJsonArrayContainsInAnyOrder(Response response, String jsonPath,
			Object... expectedElements) {
		response.then().assertThat().body(jsonPath, containsInAnyOrder(expectedElements));
	}

	public static void assertJsonPathExists(Response response, String jsonPath) {
		response.then().assertThat().body(jsonPath, notNullValue());
	}

	public static void assertJsonPathDoesNotExist(Response response, String jsonPath) {
		response.then().assertThat().body(jsonPath, nullValue());
	}

	public static void assertJsonNumberGreaterThan(Response response, String jsonPath, int expectedValue) {
		response.then().assertThat().body(jsonPath, greaterThan(expectedValue));
	}

	public static void assertJsonNumberLessThan(Response response, String jsonPath, int expectedValue) {
		response.then().assertThat().body(jsonPath, lessThan(expectedValue));
	}

	public static void assertJsonNumberGreaterThanOrEqualTo(Response response, String jsonPath, int expectedValue) {
		response.then().assertThat().body(jsonPath, greaterThanOrEqualTo(expectedValue));
	}

	public static void assertJsonNumberLessThanOrEqualTo(Response response, String jsonPath, int expectedValue) {
		response.then().assertThat().body(jsonPath, lessThanOrEqualTo(expectedValue));
	}

}
