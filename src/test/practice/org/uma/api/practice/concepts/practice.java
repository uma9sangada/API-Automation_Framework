package org.uma.api.practice.concepts;

import static org.testng.Assert.assertEquals;

import java.util.*;

import org.json.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class practice {

    private final static String BASE_URL = "https://petstore.swagger.io/v2?status=available";
    private final static String END_POINT = "/pet/findByStatus";
    private Map<String, Object> userData;

    public RequestSpecification reqspec;

    public practice() {
        reqspec = new RequestSpecBuilder().setBaseUri(BASE_URL)
        		//.setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON).build();
    }

    @Test
    void validatePostRequest() {
        userData = new HashMap<>();
        userData.put("id", 0);
        userData.put("username", "string");
        userData.put("firstName", "string");
        userData.put("lastName", "string");
        userData.put("email", "string");
        userData.put("password", "string");
        userData.put("phone", "string");
        userData.put("userStatus", 0);

        Response response = RestAssured.given()
                .spec(reqspec).queryParam("status", "available")
                .body(userData)
               // .post("/user");
                .get(END_POINT);

        assertEquals(response.getStatusCode(), 200);
        
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(""));
        
        JSONObject ui= new JSONObject(response.getBody().asPrettyString());
        
//        
//        {
//        	"id":123,
//        	"name":"uma",
//        	"items":{"place":"visakhapatnam"},
//        	"pin":[1,"sg"],
//        	"zip":[{"xero":1},{"xero":2}]
//
//        	}
       // JSONObject ui= new JSONObject(response.getBody().asPrettyString());
//       ui.getJSONArray("zip").getJSONObject(0).get("xero")
        
        
        JSONArray jarray= new JSONArray(response.getBody().asPrettyString());
        for(int i=0;i<jarray.length();i++)
        {
        	JSONObject ja=jarray.getJSONObject(i).getJSONObject("category");
        System.out.println(ja.getString("name"));
        	
        	
        }
        
        
        
        
        
        
        System.out.println(response.getBody().asPrettyString());
       // response.then().log().all(); // Optional: Log the response for debugging
    }
}