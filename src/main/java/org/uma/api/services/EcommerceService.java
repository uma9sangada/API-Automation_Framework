package org.uma.api.services;

import java.util.Map;

import org.uma.api.model.request.LoginRequest;
import org.uma.api.model.request.ProductRequest;

import io.restassured.response.Response;

public class EcommerceService extends AuthService {

	public Response userLogin(LoginRequest payload) {

		String endpoint = "/auth/";
		return login(payload, endpoint);

	}

//	public Response createProdct(ProductRequest payload,String token) {
//		
//		String endPoint="/product/add-product";
//		return postRequest(payload, endPoint, token);
//	}
	
	
	
	 public Response createProduct(ProductRequest payload, String token) {
		 String endPoint="/product/add-product";
	        requestSpecification = requestSpecification
	                .multiPart("productName", payload.getProductName())
	                .multiPart("productAddedBy", payload.getProductAddedBy())
	                .multiPart("productCategory", payload.getProductCategory())
	                .multiPart("productSubCategory", payload.getProductSubCategory())
	                .multiPart("productPrice", String.valueOf(payload.getProductPrice()))
	                .multiPart("productDescription", payload.getProductDescription())
	                .multiPart("productFor", payload.getProductFor())
	                .multiPart("productImage", payload.getProductImage());
	        return postRequest(requestSpecification,endPoint,token);
	 }

	
	
public Response createOrder(Map<String, Object> orderData,String token) {
		
		String endPoint="/order/create-order";
		return postRequest(orderData, endPoint, token);
	}

	public Response getProdct(String token, String orderId) {
		String endpoint = "order/get-orders-details?id=" + orderId;
		return getRequest(endpoint, token);
	}

	public Response deleteProdct(String token, String productId) {

		String endpoint = "/product/delete-product/" + productId;
		return deleteRequest(endpoint, token);
	}

}
