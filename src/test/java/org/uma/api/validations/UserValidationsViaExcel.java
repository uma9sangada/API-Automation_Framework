package org.uma.api.validations;

import org.testng.annotations.Test;
import org.uma.api.dto.UserDto;
import org.uma.api.services.UserService;
import org.uma.api.utils.CustomAssertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import org.uma.api.models.User;
import org.uma.api.utility.UserDataSetup;
import java.io.IOException;
import java.util.List;

public class UserValidationsViaExcel {

	private final UserService userService = new UserService();
	private List<UserDto> userDtoList; // Store the data from excel.

	@BeforeClass
	public void setupTestData() throws IOException {
		// Read data from Excel once and store it.
		UserDataSetup datasetup = new UserDataSetup();
		Object[][] data = datasetup.getAllData();
		userDtoList = java.util.Arrays.stream(data).map(row -> (UserDto) row[0]).toList(); // convert Object[][] to
																							// List<UserDto>
	}

	@Test(priority = 1)
	public void validateCreateUser() {
		// Reuse data from the list
		UserDto userDto = userDtoList.get(0); // Get the first UserDto.

		Response response = userService.createUser(userDto);

		CustomAssertions.assertStatusCode(response, 201);
		User createdUser = response.as(User.class);
		Assert.assertNotNull(createdUser.getUsername());
		Assert.assertEquals(userDto.getFirstName(), createdUser.getFirstName());
		Assert.assertEquals(userDto.getEmail(), createdUser.getEmail());
	}

	@Test(priority = 2)
	public void validateGetUser() {
		// Reuse data from the list
		UserDto userDto = userDtoList.get(1); // Get a different UserDto.
		User createdUser = userService.createUserAndReturnModel(userDto);

		Response response = userService.getUser(createdUser.getId());
		User retrievedUser = response.as(User.class);
		Assert.assertEquals(createdUser.getUsername(), retrievedUser.getUsername());
		Assert.assertEquals(createdUser.getFirstName(), retrievedUser.getFirstName());
		Assert.assertEquals(createdUser.getEmail(), retrievedUser.getEmail());
	}

	@Test(priority = 3)
	public void validateUpdateUser() {

		// Reuse data from the list
		UserDto userDto = userDtoList.get(1); // Get a different UserDto.
		User createdUser = userService.createUserAndReturnModel(userDto);

		// Create a new UserDto with only the values you want to update.
		UserDto updatedUserDto = new UserDto();
		updatedUserDto.setFirstName("UpdatedFirstName");
		updatedUserDto.setEmail("updated.email@example.com");

		// Update the user using the updatedUserDto.
		Response response = userService.updateUser(createdUser.getId(), updatedUserDto);

		CustomAssertions.assertStatusCode(response, 200);
		Response response2 = userService.getUser(createdUser.getId());
		User retrievedUser = response2.as(User.class);

		// Assert that only the updated values are changed.
		Assert.assertEquals(updatedUserDto.getFirstName(), retrievedUser.getFirstName());
		Assert.assertEquals(updatedUserDto.getEmail(), retrievedUser.getEmail());

		// Assert that other values remain the same (optional).
		Assert.assertEquals(userDto.getUsername(), retrievedUser.getUsername());
		Assert.assertEquals(userDto.getLastName(), retrievedUser.getLastName());
		Assert.assertEquals(userDto.getPassword(), retrievedUser.getPassword());
		Assert.assertEquals(userDto.getPhone(), retrievedUser.getPhone());
	}

	@Test(priority = 4)
	public void validateDeleteUser() {
		// Reuse data from the list
		UserDto userDto = userDtoList.get(1); // Get a different UserDto.
		User createdUser = userService.createUserAndReturnModel(userDto);

		Response response = userService.deleteUser(createdUser.getId());

		CustomAssertions.assertStatusCode(response, 204);

		Response getResponse = userService.getUser(createdUser.getId());

		CustomAssertions.assertStatusCode(getResponse, 404);

	}

	@Test(priority = 5)
	public void validateGetAllUsers() {
		// Reuse data from the list
		UserDto userDto1 = userDtoList.get(0); // Get a UserDto.
		UserDto userDto2 = userDtoList.get(1); // Get another UserDto.
		userService.createUser(userDto1);
		userService.createUser(userDto2);

		User[] users = userService.getAllUsers();

		Assert.assertNotNull(users);
		Assert.assertTrue(users.length >= 2);
	}
}