package org.uma.api.services;

import org.uma.api.dto.UserDto;
import org.uma.api.endpoints.*;
import org.uma.api.models.User;
import org.uma.api.utils.JsonUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.uma.api.utils.RequestSpecBuilderUtil.getRequestSpec; // Corrected package name

public class UserService {

	private final RequestSpecification requestSpec = getRequestSpec();

	public Response createUser(UserDto userDto) {
		String payload = JsonUtils.toJson(userDto);
		return UserEndpoints.createUser(requestSpec, payload);
	}

//	public User getUser(int userId) {
//		Response response = UserEndpoints.getUser(requestSpec, userId);
//		return response.as(User.class); // Use User.class for consistency
//	}

//	public Response getUser(int userId) {
//        return UserEndpoints.getUser(requestSpec, userId);
//        
	
	public Response getUser (int userId) {
        return UserEndpoints.getUser(requestSpec, userId);
    }
        
	public Response updateUser(int userId, UserDto userDto) {
		String payload = JsonUtils.toJson(userDto);
		return UserEndpoints.updateUser(requestSpec, userId, payload);
	}

	public Response deleteUser(int userId) {
		return UserEndpoints.deleteUser(requestSpec, userId);
	}

	public User[] getAllUsers() {
		Response response = UserEndpoints.getAllUsers(requestSpec);
		return response.as(User[].class); // Use User[].class for consistency
	}

	public User createUserAndReturnModel(UserDto userDto) {
		Response response = createUser(userDto);
		return response.as(User.class); // Use User.class for consistency
	}

}