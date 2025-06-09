package com.api.tests.AuthServices;

import com.api.base.AuthService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTest {

    @Test(testName = "Forgot Password Test")
    public void testForgotPassword() {
        AuthService athService = new AuthService();
        Response response = athService.forgotPassword("test@test.com");
        System.out.println("Response: " + response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 500, "Expected status code 500 but got: " + response.getStatusCode());
    }
}
