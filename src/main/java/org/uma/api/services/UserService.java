package org.uma.api.services;

import org.uma.api.model.request.*;
import org.uma.api.utils.JsonUtils;
import io.restassured.response.Response;

public class UserService extends BaseService {

	private static final String BASE_PATH = "/users";

	public Response createUser(UserRequest payload) {
		return postRequest(payload, BASE_PATH);
	}

	public Response getUser(int userId) {
		return getRequest(BASE_PATH + userId);
	}

	public Response updateUser(int userId, UserRequest user) {
		String payload = JsonUtils.toJson(user);
		return putRequest(payload, BASE_PATH + userId);
	}

	public Response deleteUser(int userId) {
		return getRequest(BASE_PATH + userId);
	}

	public Response getAllUsers() {
		return getRequest(BASE_PATH);
	}

}