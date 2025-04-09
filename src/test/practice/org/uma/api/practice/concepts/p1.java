package org.uma.api.practice.concepts;

import java.util.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class p1 {
	
	/*
{
"id":123,
"name":"uma",
"items":{"place":"visakhapatnam"},
"pin":[1,"sg"],
"zip":[{"xero":1},{"xero":2}]
}

*/



	@Test
	public void pr() throws JsonProcessingException {

		HashMap<String,Object> body = new HashMap<>();
		body.put("id", 123);
		body.put("name", "uma");

		Object[] values = { 1, "sg" };
		body.put("pin", values);

		HashMap<String, Object> ut = new HashMap<>();
		ut.put("place", "visakhapatnam");
		body.put("items", ut);
		List<HashMap<String, Object>> zipList = new ArrayList<>();

		HashMap<String, Object> zipMap1 = new HashMap<>();
		zipMap1.put("xero", 1);
		zipList.add(zipMap1);

		HashMap<String, Object> zipMap2 = new HashMap<>();
		zipMap2.put("xero", 2);
		zipList.add(zipMap2);

		body.put("zip", zipList);

		String s = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(body);
		System.out.println(s);
	}

}
