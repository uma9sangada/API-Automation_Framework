package org.uma.api.practice.concepts;
import org.testng.annotations.Test;

// Required to import these
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator; // Correct import
import io.restassured.response.Response;

public class JSONAndXMLSchemaValidations {

    @Test
    void schemaValidation() {

        File schemaFile = new File("C:\\Users\\umasa\\Downloads\\APIFramework\\APIFramework\\src\\test\\java\\resources\\Schema.json");

        RestAssured.baseURI = "http://localhost:3000";

        Response res = when().get("/students");

        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
    }
}