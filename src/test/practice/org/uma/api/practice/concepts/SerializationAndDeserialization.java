package org.uma.api.practice.concepts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationAndDeserialization {

	// jackson package with objectmapper class
	@Test
	void serialixation() throws JsonProcessingException {

		// use pojo as well
		HashMap data = new HashMap<>();
		data.put("name", "uma");
		data.put("location", "kaks");
		data.put("phone", "987654");
		String[] coursesArr = { "JAVA", "C++" };
		data.put("courses", coursesArr);

		String nestedPayload = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(data);
		System.out.println(nestedPayload);

	}

	// @Test
	void deserialixation() throws JsonProcessingException {

		String jsonData = "{\r\n" + "  \"courses\" : [ \"JAVA\", \"C++\" ],\r\n" + "  \"phone\" : \"987654\",\r\n"
				+ "  \"name\" : \"uma\",\r\n" + "  \"location\" : \"kaks\"\r\n" + "}";

		PojoRequestPayLoad reqload = new ObjectMapper().readValue(jsonData, PojoRequestPayLoad.class);// convert json to

		// pojo
		System.out.println(reqload.getLocation());
		System.out.println(reqload.getName());
		System.out.println(reqload.getPhone());
		System.out.println(reqload.getCourses()[0]);
		System.out.println(reqload.getCourses()[1]);

	}

}
