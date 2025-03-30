package org.uma.api.practice.concepts;

import java.io.File;
//Required to import these
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Given;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FileUploadAndDownloadAPIs {

	@Test(priority = 1)
	void sinfleFileUpload() {
		File myfile1 = new File("C:\\Users\\umasa\\Downloads\\API upload download\\test1.txt");

		RestAssured.baseURI = "http://localhost:8080";

		Response resp = given().multiPart("file", myfile1).contentType("multipart/form-data").when()
				.post("/uploadFile");

		resp.then().assertThat().statusCode(200).body("fileName", equalTo("test1.txt")).log().ifValidationFails();

	}

	// @Test
	void multiFileFileUpload() {
		File myfile1 = new File("C:\\Users\\umasa\\Downloads\\API upload download\\test1.txt");
		File myfile2 = new File("C:\\Users\\umasa\\Downloads\\API upload download\\test2.txt");

		// File fileArr[]= {myfile1,myfile2};
		RestAssured.baseURI = "http://localhost:8080"; // Set base URI

		Response resp = given()
				// .multiPart("files", fileArr) some api supports
				.multiPart("files", myfile1).multiPart("files", myfile2).contentType("multipart/form-data").when()
				.post("/uploadMultipleFiles"); // Use relative path

		resp.then().assertThat().statusCode(200).body("[0].fileName", equalTo("test1.txt"))
				.body("[1].fileName", equalTo("test2.txt")).log().all(); // Log only on failure

	}

	@Test(priority = 2, dependsOnMethods = { "sinfleFileUpload" })
	void sinfleFileDownload() {
		RestAssured.baseURI = "http://localhost:8080"; // Set base URI

		Response resp = when().get("/downloadFile/test1.txt"); // Use relative path

		resp.then().assertThat().statusCode(200).log().body();

	}

}
