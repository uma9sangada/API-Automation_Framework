package org.uma.api.validations;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.uma.api.model.request.LoginRequest;
import org.uma.api.model.request.ProductRequest;
import org.uma.api.model.response.LoginResponse;
import org.uma.api.services.EcommerceService;

import io.restassured.response.Response;

public class EcommerceValidation {

    EcommerceService ecommerceService = new EcommerceService();
    LoginRequest loginRequest = new LoginRequest();
    private String token = "";
    private String productId = "";
    private String orderId = "";
    private String userId = "";

    @Test(priority = 1, groups = { "LoginAPI", "sanity" }, description = "Validate creating a new user")
    public void validateLogin() {

        loginRequest.setUserEmail("umasangada98@gmail.com");
        loginRequest.setUserPassword("T@bby23519");

        Response response = ecommerceService.userLogin(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(loginResponse.getMessage(), "Login Successfully");
        Assert.assertTrue(!loginResponse.getUserId().isBlank());

        if (!loginResponse.getToken().isBlank()) { //Fixed: removed redundant second condition
            token = loginResponse.getToken();
            userId = loginResponse.getUserId();
        }

    }

    @Test(priority = 2, groups = { "E2e functionality", "sanity" }, description = "Validate creating a new product")
    public void validatePostProduct() {
        File myfile = new File("C:\\Users\\umasa\\Downloads\\lantern-isolated-transparent-background");

        ProductRequest proctRequestPayload = new ProductRequest("clothes", userId, "fashion", "shirts", 1100,
                "Polo company", "men", myfile);

        Response response = ecommerceService.createProduct(proctRequestPayload, token);

        Assert.assertEquals(response.getStatusCode(), 201);
        String responseBody = response.getBody().asString();
        JSONObject responseJson = new JSONObject(responseBody);

        Assert.assertEquals(responseJson.getString("message"), "Product Added Successfully");
        Assert.assertTrue(!responseJson.getString("productId").isBlank());

        if (!responseJson.getString("productId").isBlank()) {
            productId = responseJson.getString("productId");
        }
    }

    @Test(priority = 3, groups = { "E2e functionality", "sanity" }, description = "Validate creating a new order")
    public void validatePostOrder() {

        Map<String, Object> orderData = new HashMap<>();
        List<Map<String, String>> ordersList = new ArrayList<>();
        Map<String, String> order = new HashMap<>();

        order.put("country", "India");
        order.put("productOrderedId", productId); // Use the provided productId

        ordersList.add(order);
        orderData.put("orders", ordersList);

        Response response = ecommerceService.createOrder(orderData, token);

        Assert.assertEquals(response.getStatusCode(), 201);
        String responseBody = response.getBody().asString();
        JSONObject responseJson = new JSONObject(responseBody);

        Assert.assertEquals(responseJson.getString("message"), "Order Placed Successfully");
        Assert.assertTrue(responseJson.getJSONArray("orders").length() > 0 && !responseJson.getJSONArray("orders").getString(0).isBlank()); //fixed assertion to check if array has size and the element is not blank

        orderId = responseJson.getJSONArray("orders").getString(0);
    }

    @Test(priority = 4, groups = { "E2e functionality", "sanity" }, description = "Validate getting the product")
    public void validateGetProduct() {

        Response response = ecommerceService.getProdct(token, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);
        String responseBody = response.getBody().asString();
        JSONObject responseJson = new JSONObject(responseBody);

        Assert.assertEquals(responseJson.getString("message"), "Orders fetched for customer Successfully");
        Assert.assertTrue(responseJson.getJSONArray("data").getJSONObject(0).getString("productId").equals(productId)); //fixed assertion to check the product id returned is the same as the one created.
    }

    @Test(priority = 5, groups = { "E2e functionality", "sanity" }, description = "Validate deleting the created product")
    public void validateDeleteProduct() {

        Response response = ecommerceService.deleteProdct(token, productId);

        Assert.assertEquals(response.getStatusCode(), 200);

        String responseBody = response.getBody().asString();
        JSONObject responseJson = new JSONObject(responseBody);

        Assert.assertEquals(responseJson.getString("message"), "Product Deleted Successfully");

    }
}