package org.uma.api.practice.concepts;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;



//Required to import these
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PayloadTypes {
	/*
	 * We can create request PayLoad in 4 ways: 1. HashMap 2. Using org.json 3.
	 * using POJO 4. using External JSON file
	 */

	// @Test
	void testPOSTUsingHashMap() {
		HashMap data = new HashMap<>();
		data.put("name", "uma");
		data.put("location", "kaks");
		data.put("phone", "987654");
		String[] coursesArr = { "JAVA", "C++" };
		data.put("courses", coursesArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("uma")).body("location", equalTo("kaks"))
				.body("phone", equalTo("987654")).body("courses[0]", equalTo("JAVA")).log().all();
	}

	// need to import <!-- https://mvnrepository.com/artifact/org.json/json -->
	// @Test
	void testPOSTUsingJSONLibrary() {
		JSONObject data = new JSONObject();
		data.put("name", "uma");
		data.put("location", "kaks");
		data.put("phone", "987654");
		String[] coursesArr = { "JAVA", "C++" };
		data.put("courses", coursesArr);

		given().contentType("application/json").body(data.toString()).when().post("http://localhost:3000/students")
				.then().statusCode(201).body("name", equalTo("uma")).body("location", equalTo("kaks"))
				.body("phone", equalTo("987654")).body("courses[0]", equalTo("JAVA")).log().all();
	}

	// @Test
	void testPOSTUsingPOJO() {
		PojoRequestPayLoad data = new PojoRequestPayLoad();
		data.setName("uma");
		data.setLocation("kaks");
		data.setPhone("987654");
		String[] coursesArr = { "JAVA", "C++" };
		data.setCourses(coursesArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("uma")).body("location", equalTo("kaks"))
				.body("phone", equalTo("987654")).body("courses[0]", equalTo("JAVA")).log().all();
	}

	@Test
	void testPOSTUsingExternalJson() throws FileNotFoundException {
		File f = new File(
				"C:\\Users\\umasa\\Downloads\\APIFramework\\APIFramework\\src\\test\\java\\resources\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);

		given().contentType("application/json").body(data.toString()).when().post("http://localhost:3000/students")
				.then().statusCode(201).body("name", equalTo("uma")).body("location", equalTo("kaks"))
				.body("phone", equalTo("987654")).body("courses[0]", equalTo("JAVA")).log().all();
	}

	// @Test
	void testDelete() {
		when().delete("http://localhost:3000/students/e0b3").then().statusCode(200);

	}

}
