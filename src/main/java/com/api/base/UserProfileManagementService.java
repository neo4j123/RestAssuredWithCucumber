package com.api.base;

import io.restassured.response.Response;

public class UserProfileManagementService extends BaseService{

    private static final String BASE_PATH = "api/users/";

    public Response getProfile(String token){
        setAuthToken(token);
        return GETRequest(BASE_PATH + "profile");
    }
}
