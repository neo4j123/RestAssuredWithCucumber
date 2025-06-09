package com.api.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestCases {

    public static final String token = "1b06e012f1043f4244ae7b06e5312faa2725474883baa9c6262442d03ff37e7e";

@Test(testName = "Get Server Status")
    public void getStatus(){

        RestAssured.baseURI = "https://simple-tool-rental-api.glitch.me";
        given().header("Content-Type", "application/json")
            .when()
            .get("/status")
            .then()
            .statusCode(200)
            .log().all();

    }

    @Test(testName = "Get All Tools")
    public void getAllTools(){

        RestAssured.baseURI = "https://simple-tool-rental-api.glitch.me";
        given().header("Content-Type", "application/json")
                .when()
                .get("/tools")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test(testName = "Get All Tools By Parameter")
    public void getAllToolsByParameter(){

        RestAssured.baseURI = "https://simple-tool-rental-api.glitch.me";
        given()
                .queryParams("category", "plumbing", "available", "true", "results", "10")
                .header("Content-Type", "application/json")
                .when()
                .get("/tools")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test(testName = "Get a single tool using ID")
    public void getToolByToolId(){

        RestAssured.baseURI = "https://simple-tool-rental-api.glitch.me";
        given()
                .queryParams("user-manual", "true").pathParams("toolId", "1225")
                .header("Content-Type", "application/json")
                .when()
                .get("/tools/{toolId}")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test(testName = "Create a token")
    public void getToken(){

        RestAssured.baseURI = "https://simple-tool-rental-api.glitch.me";
        given().header("Content-Type", "application/json")
                .body("{\n" +
                        "   \"clientName\": \"Akash\",\n" +
                        "   \"clientEmail\": \"srivastava.akash42@gmail.com\"\n" +
                        "}")
                .when()
                .post("/api-clients")
                .then()
                .statusCode(409) // Assuming the API returns 409 for duplicate clients
                .log().all();
    }
}
