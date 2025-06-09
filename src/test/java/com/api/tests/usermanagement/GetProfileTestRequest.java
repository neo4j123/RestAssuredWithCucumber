package com.api.tests.usermanagement;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.requests.LoginRequest;
import com.api.models.responses.LoginResponse;
import com.api.models.responses.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.api.listeners.TestListener.class)
public class GetProfileTestRequest {

    @Test(testName = "Get the profile of the user", description = "This test retrieves the profile of the user after logging in")
    public void getProfileTest() {

        AuthService athService = new AuthService();
        Response response = athService.createToken(new LoginRequest("srivastava.akash42@gmail.com", "RestInPeace@123"));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        UserProfileManagementService userProfile = new UserProfileManagementService();
        response = userProfile.getProfile(loginResponse.getToken());
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        Assert.assertEquals(userProfileResponse.getUsername(), "srivastava.akash42@gmail.com",
                "Username does not match the expected value");;
    }
}
