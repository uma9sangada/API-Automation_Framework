package org.uma.api.services.ecommerce;

import org.uma.api.model.request.ProductRequest;
import org.uma.api.services.BaseService;

import io.restassured.response.Response;

public class ProductFacadeService extends BaseService {
	
	
	
	private static final String END_POINT = "/product/add-product";

	
	public Response createProdct(ProductRequest payload,String token )
	{
		return postRequest(payload, END_POINT,token) ;
	}

}
