package org.uma.api.validations;

import org.testng.annotations.Test;
import org.uma.api.model.request.UserRequest;
import org.uma.api.services.UserService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import org.uma.api.utility.UserDataSetup;
import java.io.IOException;
import java.util.List;

public class UserValidationsViaExcel {

    private final UserService userService = new UserService();
    private List<UserRequest> userList;
    private UserRequest createdUser;
    private UserRequest updatedUser;

    @BeforeClass
    public void setupTestData() throws IOException {
        UserDataSetup datasetup = new UserDataSetup();
        Object[][] data = datasetup.getAllData();
        userList = java.util.Arrays.stream(data).map(row -> (UserRequest) row[0]).toList();
        createdUser = userList.get(0);
        updatedUser = new UserRequest();
    }

    @Test(priority = 1)
    public void validateCreateUser() {
        Response response = userService.createUser(createdUser);
        Assert.assertEquals(response.getStatusCode(), 201);
        UserRequest createdUserResponse = response.as(UserRequest.class);
        Assert.assertNotNull(createdUserResponse.getUsername());
        Assert.assertEquals(createdUser.getFirstName(), createdUserResponse.getFirstName());
        Assert.assertEquals(createdUser.getEmail(), createdUserResponse.getEmail());
    }

    @Test(priority = 2)
    public void validateGetUser() {
        Response response = userService.getUser(createdUser.getId());
        Assert.assertEquals(response.getStatusCode(), 200);
        UserRequest retrievedUser = response.as(UserRequest.class);
        Assert.assertEquals(createdUser.getUsername(), retrievedUser.getUsername());
        Assert.assertEquals(createdUser.getFirstName(), retrievedUser.getFirstName());
        Assert.assertEquals(createdUser.getEmail(), retrievedUser.getEmail());
    }

    @Test(priority = 3)
    public void validateUpdateUser() {
        updatedUser.setFirstName("UpdatedFirstName");
        updatedUser.setEmail("updated.email@example.com");

        Response response = userService.updateUser(createdUser.getId(), updatedUser);
        Assert.assertEquals(response.getStatusCode(), 200);

        Response response2 = userService.getUser(createdUser.getId());
        Assert.assertEquals(response2.getStatusCode(), 200);
        UserRequest retrievedUser = response2.as(UserRequest.class);

        Assert.assertEquals(updatedUser.getFirstName(), retrievedUser.getFirstName());
        Assert.assertEquals(updatedUser.getEmail(), retrievedUser.getEmail());

        Assert.assertEquals(createdUser.getUsername(), retrievedUser.getUsername());
        Assert.assertEquals(createdUser.getLastName(), retrievedUser.getLastName());
        Assert.assertEquals(createdUser.getPassword(), retrievedUser.getPassword());
        Assert.assertEquals(createdUser.getPhone(), retrievedUser.getPhone());
    }

    @Test(priority = 4)
    public void validateDeleteUser() {
        UserRequest deleteUser = userList.get(1);
        Response createResponse = userService.createUser(deleteUser);
        Assert.assertEquals(createResponse.getStatusCode(), 201);
        UserRequest userToDelete = createResponse.as(UserRequest.class);
        Response response = userService.deleteUser(userToDelete.getId());
        Assert.assertEquals(response.getStatusCode(), 204);
        Response getResponse = userService.getUser(userToDelete.getId());
        Assert.assertEquals(getResponse.getStatusCode(), 404);
    }

    @Test(priority = 5)
    public void validateGetAllUsers() {
        UserRequest user1 = userList.get(0);
        UserRequest user2 = userList.get(1);
        userService.createUser(user1);
        userService.createUser(user2);
        Response getAllResponse = userService.getAllUsers();
        Assert.assertEquals(getAllResponse.getStatusCode(), 200);
        UserRequest[] users = getAllResponse.as(UserRequest[].class);
        Assert.assertNotNull(users);
        Assert.assertTrue(users.length >= 2);
    }
}