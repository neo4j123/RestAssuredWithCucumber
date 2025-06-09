package com.api.tests.AuthServices;

import com.api.base.AuthService;
import com.api.models.requests.LoginRequest;
import com.api.models.responses.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest {
    /**
     * This test verifies if we can create a new API client token.
     * It sends a POST request to the /api-clients endpoint with a JSON body containing clientName and clientEmail.
     * The expected response status code is 409.
     */
    @Test(testName = "Verify if we can create a new login token")
    public void createLoginTOken() {
        AuthService auth = new AuthService();
        LoginRequest createToken = new LoginRequest("srivastava.akash42@gmail.com", "RestInPeace@123");
        Response response = auth.createToken(createToken);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println("Token Is: "+loginResponse.getToken());
        System.out.println("ID Is: "+loginResponse.getId());
        System.out.println("Email Is: "+loginResponse.getEmail());
        System.out.println("Type Is: "+loginResponse.getType());
        System.out.println("Username Is: "+loginResponse.getUsername());
        System.out.println("Role Is: "+loginResponse.getRoles());
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(testName = "Verify if we can create illegal client token")
    public void createInvalidToken() {
        LoginRequest createToken = new LoginRequest(); // Empty clientName and clientEmail
        AuthService auth = new AuthService();
        Response response = auth.createToken(createToken);
        System.out.println(response.prettyPeek());
        Assert.assertEquals(response.getStatusCode(), 500);

    }
}
