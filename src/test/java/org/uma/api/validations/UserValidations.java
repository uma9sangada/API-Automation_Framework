package org.uma.api.validations;

import org.testng.annotations.Test;
import org.uma.api.model.request.UserRequest;
import org.uma.api.model.response.UserResponse;
import org.uma.api.services.UserService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidations {

	private final UserService userService = new UserService();
	private UserRequest originalUser;
	private Faker fake;
	

	@BeforeClass
	public void setupTestData() {
		
		fake = new Faker();
		originalUser = new UserRequest();
		originalUser.setUsername(fake.name().username());
		originalUser.setFirstName(fake.name().firstName());
		originalUser.setLastName(fake.name().lastName());
		originalUser.setEmail(fake.internet().emailAddress());
		originalUser.setPassword(fake.internet().password());
		originalUser.setPhone(fake.phoneNumber().phoneNumber());
		originalUser.setUserStatus(0);
		
	}

	@Test(priority = 1, groups = { "UserAPI", "sanity" }, description = "Validate creating a new user")
	public void validatePostUser() {
		
		Response createResponse = userService.createUser(originalUser);
	
		Assert.assertEquals(createResponse.getStatusCode(), 200);
		
	}

	@Test(priority = 2, groups = { "UserAPI", "regression" }, description = "Validate retrieving a user")
	public void validateGetUser() {
		
		Response response = userService.getUser(originalUser.getUsername());
		
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		UserResponse retrievedUser = response.as(UserResponse.class);
		Assert.assertEquals(originalUser.getUsername(), retrievedUser.getUsername());
		Assert.assertEquals(originalUser.getFirstName(), retrievedUser.getFirstName());
		Assert.assertEquals(originalUser.getEmail(), retrievedUser.getEmail());
	
	}

	@Test(priority = 3, groups = { "UserAPI", "regression" }, description = "Validate updating a user")
	public void validateUpdateUser() {
		
		originalUser.setFirstName(fake.name().firstName());
		originalUser.setEmail(fake.internet().emailAddress());

		Response response = userService.updateUser(originalUser.getUsername(), originalUser);
	
		Assert.assertEquals(response.getStatusCode(), 200);

		UserResponse retrievedUser = userService.getUser(originalUser.getUsername()).as(UserResponse.class);
		

		Assert.assertEquals(originalUser.getFirstName(), retrievedUser.getFirstName());
		Assert.assertEquals(originalUser.getEmail(), retrievedUser.getEmail());
	
	}

	@Test(priority = 4, groups = { "UserAPI", "regression" }, description = "Validate deleting a user")
	public void validateDeleteUser() {
		
		Response response = userService.deleteUser(originalUser.getUsername());
		
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}
}