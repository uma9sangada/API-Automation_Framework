package org.uma.api.services;

import java.util.HashMap;

import org.uma.api.model.request.LoginRequest;
import org.uma.api.model.request.SignupRequest;

import io.restassured.response.Response;

public class AuthService extends BaseService{
	
	//private static final String BASE_PATH = "/api/auth/";

	public Response login(LoginRequest payload,String BASE_PATH) {
		return postRequest(payload, BASE_PATH + "login");
	}

	public Response signUp(SignupRequest payload,String BASE_PATH) {
		return postRequest(payload, BASE_PATH + "signup");
	}

	public Response forgotPassword(String emailAddress,String BASE_PATH) {
		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("email", emailAddress);
		return postRequest(payload, BASE_PATH + "forgot-password");

	}

}
