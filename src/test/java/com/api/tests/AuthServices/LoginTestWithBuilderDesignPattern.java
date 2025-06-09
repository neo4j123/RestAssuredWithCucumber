package com.api.tests.AuthServices;

import com.api.base.AuthService;
import com.api.models.requests.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class LoginTestWithBuilderDesignPattern {

    @Test
    public void loginTest(){
        // Using Builder Design Pattern to create a LoginRequest object
        LoginRequest loginRequest = new LoginRequest.Builder().username("").password("").build();
        AuthService authService = new AuthService();
        Response response = authService.createToken(loginRequest);
        System.out.println(response.asPrettyString());

    }
}
