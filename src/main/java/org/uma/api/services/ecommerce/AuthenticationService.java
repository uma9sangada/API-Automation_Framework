package org.uma.api.services.ecommerce;

import org.uma.api.model.request.LoginRequest;
import org.uma.api.model.request.UserRequest;
import org.uma.api.services.AuthService;
import org.uma.api.services.BaseService;

import io.restassured.response.Response;

public class AuthenticationService extends AuthService {
	
	private static final String BASE_PATH = "/auth/";


	public Response userLogin(LoginRequest payload)
	{
		return login(payload,BASE_PATH);
		
	}
	
	
}
