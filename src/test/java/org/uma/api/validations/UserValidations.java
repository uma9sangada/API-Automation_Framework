package org.uma.api.validations;

import org.testng.annotations.Test;
import org.uma.api.dto.UserDto;
import org.uma.api.services.UserService;
import org.uma.api.utils.CustomAssertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import org.uma.api.models.User;

public class UserValidations {

    private final UserService userService = new UserService();
    private User createdUser; // Store the created user.
    private UserDto originalUserDto; // Store the original userDto

    @BeforeClass
    public void setupTestData() {
        // Create a user once and store it.
        originalUserDto = new UserDto(0, "Original", "Name", "original.email@example.com", "password", "123-456-7890", "originaluser");
        createdUser = userService.createUserAndReturnModel(originalUserDto);
    }

    @Test(priority = 1)
    public void validateCreateUser() {
        // Reuse the created user's data.
        Response response = userService.createUser(originalUserDto);

        CustomAssertions.assertStatusCode(response, 201);
        User createdUserResponse = response.as(User.class);
        Assert.assertNotNull(createdUserResponse.getUsername());
        Assert.assertEquals(originalUserDto.getFirstName(), createdUserResponse.getFirstName());
        Assert.assertEquals(originalUserDto.getEmail(), createdUserResponse.getEmail());
    }

    @Test(priority = 2)
    public void validateGetUser() {
        // Reuse the created user.
        User retrievedUser = userService.getUser(createdUser.getId()).as(User.class);

        Assert.assertEquals(createdUser.getUsername(), retrievedUser.getUsername());
        Assert.assertEquals(createdUser.getFirstName(), retrievedUser.getFirstName());
        Assert.assertEquals(createdUser.getEmail(), retrievedUser.getEmail());
    }

    @Test(priority = 3)
    public void validateUpdateUser() {
        // Update only specific fields.
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setFirstName("UpdatedFirstName");
        updatedUserDto.setEmail("updated.email@example.com");

        Response response = userService.updateUser(createdUser.getId(), updatedUserDto);

        CustomAssertions.assertStatusCode(response, 200);
        User retrievedUser = userService.getUser(createdUser.getId()).as(User.class);

        Assert.assertEquals(updatedUserDto.getFirstName(), retrievedUser.getFirstName());
        Assert.assertEquals(updatedUserDto.getEmail(), retrievedUser.getEmail());

        // Assert that other values remain the same.
        Assert.assertEquals(originalUserDto.getUsername(), retrievedUser.getUsername());
        Assert.assertEquals(originalUserDto.getLastName(), retrievedUser.getLastName());
        Assert.assertEquals(originalUserDto.getPassword(), retrievedUser.getPassword());
        Assert.assertEquals(originalUserDto.getPhone(), retrievedUser.getPhone());
       
    }

    @Test(priority = 4)
    public void validateDeleteUser() {
        // Reuse the created user.
        Response response = userService.deleteUser(createdUser.getId());

        CustomAssertions.assertStatusCode(response, 204);

        Response getResponse = userService.getUser(createdUser.getId());

        CustomAssertions.assertStatusCode(getResponse, 404);
    }

    @Test(priority = 5)
    public void validateGetAllUsers() {
        // Create an additional user.
        userService.createUser(new UserDto(0, "User", "Two", "user2@example.com", "password", "123-456-7890", "user2"));

        User[] users = userService.getAllUsers();

        Assert.assertNotNull(users);
        Assert.assertTrue(users.length >= 2);
    }
}