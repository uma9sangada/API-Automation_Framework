package org.uma.api.validations;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.uma.api.model.request.LoginRequest;
import org.uma.api.model.request.ProductRequest;
import org.uma.api.model.response.LoginResponse;
import org.uma.api.services.ecommerce.AuthenticationService;
import org.uma.api.services.ecommerce.ProductFacadeService;

import io.restassured.response.Response;

public class EcommerceValidation {

	AuthenticationService authenticationService = new AuthenticationService();
	private String token = "";

	@Test(priority = 1, groups = { "LoginAPI", "sanity" }, description = "Validate creating a new user")
	public void validLogin() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("umasangada98@gmail.com");
		loginRequest.setUserPassword("T@bby23519");

		Response response = authenticationService.userLogin(loginRequest);
		LoginResponse loginResponse = response.as(LoginResponse.class);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(loginResponse.getMessage(), "Login Successfully");
		Assert.assertTrue(!loginResponse.getUserId().isBlank());

		if (!loginResponse.getToken().isBlank()) {
			token = loginResponse.getToken();
		}

	}

	@Test(priority = 2, groups = { "LoginAPI", "sanity" }, description = "Validate creating a new user")
	public void validateAddProduct() {
		ProductRequest proctRequestPayload = new ProductRequest();

		Response response = new ProductFacadeService().createProdct(proctRequestPayload,token);

		Assert.assertEquals(response.getStatusCode(), 201);
		String responseBody = response.getBody().asString();
		JSONObject responseJson = new JSONObject(responseBody);

		Assert.assertEquals(responseJson.getString("message"), "Product Added Successfully");
		Assert.assertTrue(!responseJson.getString("productId").isBlank());
	}

}
