package com.api.base;

import com.api.models.requests.LoginRequest;
import com.api.models.requests.ResetPasswordRequest;
import io.restassured.response.Response;

import java.util.HashMap;

public class AuthService extends BaseService{

    private static final String BASE_PATH = "/api/auth/";


    public Response createToken(LoginRequest payload) {
        return POSTRequest(payload, BASE_PATH + "login");
    }

    public Response resetPwd(ResetPasswordRequest payload) {
        return POSTRequest(payload, BASE_PATH + "reset-password");
    }

    public Response forgotPassword(String emailAddress){
        HashMap<String, String> payload = new HashMap<>();
        payload.put("email", emailAddress);
        return POSTRequest(payload, BASE_PATH + "forgot-password");
    }


}
