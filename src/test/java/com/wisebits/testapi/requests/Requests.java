package com.wisebits.testapi.requests;

import com.wisebits.testapi.models.CreateUserDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Requests {
    public Response createUserRequest(CreateUserDto user) {
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType("multipart/form-data")
                .multiPart("username", user.username())
                .multiPart("email",user.email())
                .multiPart("password", user.password())
                .when()
                .post("/user/create")
                .then()
                .extract().response();
    }

    public Response getAllUsersRequest() {
        return RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/user/get")
                .then()
                .extract().response();
    }
}
