package com.api.tests.AuthServices;

import com.api.base.AuthService;
import com.api.models.requests.LoginRequest;
import com.api.models.requests.ResetPasswordRequest;
import com.api.models.responses.LoginResponse;
import com.api.models.responses.ResetPasswordResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetPasswordTest {

    @Test(testName = "Verify if we can reset a password")
    public void resetPasswordTest() {
        AuthService auth = new AuthService();
        ResetPasswordRequest resetPassword = new ResetPasswordRequest("Test@123", "Test@123", "token");
        Response response = auth.resetPwd(resetPassword);
        ResetPasswordResponse resetPasswordResponse = response.as(ResetPasswordResponse.class);
        System.out.println("Token Is: "+resetPasswordResponse.getMessage());
        System.out.println(response.asPrettyString());
        Assert.assertEquals(resetPasswordResponse.getMessage(), "Invalid reset token");
        Assert.assertEquals(response.getStatusCode(), 400);

    }
}
