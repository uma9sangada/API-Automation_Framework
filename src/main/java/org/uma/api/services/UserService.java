package org.uma.api.services;

import org.uma.api.model.request.*;
import org.uma.api.utils.JsonUtils;
import io.restassured.response.Response;

public class UserService extends BaseService {

	private static final String BASE_PATH = "/user/";

	public Response createUser(UserRequest payload) {
		return postRequest(payload, BASE_PATH);
	}

	public Response getUser(String userName) {
		return getRequest(BASE_PATH + userName);
	}

	public Response updateUser(String userName, UserRequest user) {
		String payload = JsonUtils.toJson(user);
		return putRequest(payload, BASE_PATH + userName);
	}

	public Response deleteUser(String userName) {
		return getRequest(BASE_PATH + userName);
	}

	public Response getAllUsers() {
		return getRequest(BASE_PATH);
	}

}