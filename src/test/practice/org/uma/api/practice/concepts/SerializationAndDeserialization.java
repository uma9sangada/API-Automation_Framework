package org.uma.api.practice.concepts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationAndDeserialization {

	// jackson package with objectmapper class
	//@Test
	void serialixation() throws JsonProcessingException {

		// use pojo as well
		HashMap<String,Object> data = new HashMap<>();
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
		
		String v="{\r\n"
				+ "\"id\":123,\r\n"
				+ "\"name\":\"uma\",\r\n"
				+ "\"items\":{\"place\":\"visakhapatnam\"},\r\n"
				+ "\"pin\":[1,\"sg\"],\r\n"
				+ "\"zip\":[{\"xero\":1},{\"xero\":2}]\r\n"
				+ "\r\n"
				+ "}";

		PojoRequestPayLoad reqload = new ObjectMapper().readValue(jsonData, PojoRequestPayLoad.class);// convert json to

		// pojo
		System.out.println(reqload.getLocation());
		System.out.println(reqload.getName());
		System.out.println(reqload.getPhone());
		System.out.println(reqload.getCourses()[0]);
		System.out.println(reqload.getCourses()[1]);

	}
	
	
	//@Test
	public void Serialization()
	{
	 
		Map<String,Object> map = new HashMap<>();
		map.put("name", "uma");
		map.put("number", 123456789);
		Object[] details= {530002,"Nowroji road",true};
		map.put("Address", details);
		try {
		String json= new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
		System.out.println(json);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
