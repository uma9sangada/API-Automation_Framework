package org.uma.api.validations;

import org.testng.annotations.Test;
import org.uma.api.model.request.UserRequest;
import org.uma.api.services.UserService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;

public class UserValidations {

    private final UserService userService = new UserService();
    private UserRequest createdUser;
    private UserRequest originalUser;

    @BeforeClass
    public void setupTestData() {
        originalUser = new UserRequest();
        originalUser.setUsername("Original");
        originalUser.setFirstName("Name");
        originalUser.setEmail("original.email@example.com");
        originalUser.setPassword("password");
        originalUser.setPhone("123-456-7890");
        originalUser.setUserStatus(0);

        Response createResponse = userService.createUser(originalUser);
        Assert.assertEquals(createResponse.getStatusCode(), 201);
        createdUser = createResponse.as(UserRequest.class);
    }

    @Test(priority = 1)
    public void validateGetUser() {
        Response response = userService.getUser(createdUser.getId());
        Assert.assertEquals(response.getStatusCode(), 200);
        UserRequest retrievedUser = response.as(UserRequest.class);
        Assert.assertEquals(createdUser.getUsername(), retrievedUser.getUsername());
        Assert.assertEquals(createdUser.getFirstName(), retrievedUser.getFirstName());
        Assert.assertEquals(createdUser.getEmail(), retrievedUser.getEmail());
    }

    @Test(priority = 2)
    public void validateUpdateUser() {
        UserRequest updatedUser = new UserRequest();
        updatedUser.setFirstName("UpdatedFirstName");
        updatedUser.setEmail("updated.email@example.com");

        Response response = userService.updateUser(createdUser.getId(), updatedUser);
        Assert.assertEquals(response.getStatusCode(), 200);

        UserRequest retrievedUser = userService.getUser(createdUser.getId()).as(UserRequest.class);
        Assert.assertEquals(updatedUser.getFirstName(), retrievedUser.getFirstName());
        Assert.assertEquals(updatedUser.getEmail(), retrievedUser.getEmail());

        Assert.assertEquals(originalUser.getUsername(), retrievedUser.getUsername());
        Assert.assertEquals(originalUser.getLastName(), retrievedUser.getLastName());
        Assert.assertEquals(originalUser.getPassword(), retrievedUser.getPassword());
        Assert.assertEquals(originalUser.getPhone(), retrievedUser.getPhone());
    }

    @Test(priority = 3)
    public void validateDeleteUser() {
        Response response = userService.deleteUser(createdUser.getId());
        Assert.assertEquals(response.getStatusCode(), 204);

        Response getResponse = userService.getUser(createdUser.getId());
        Assert.assertEquals(getResponse.getStatusCode(), 404);
    }
}