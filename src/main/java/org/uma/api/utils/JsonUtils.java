package org.uma.api.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT); // Pretty
	// print
	// JSON

	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			e.printStackTrace(); // Consider more robust error handling
			return null; // Or throw a RuntimeException
		}
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace(); // Consider more robust error handling
			return null; // Or throw a RuntimeException
		}
	}

}
