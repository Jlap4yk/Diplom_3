package ru.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;


public class UserClient extends BaseApi {

    private static final String API_REGISTER_USER = "api/auth/register";
    private static final String API_LOGIN_USER = "api/auth/login";
    private static final String API_DELETE_USER = "api/auth/user";

    @Step("Создание пользователя")
    public Response createNewUser(User user) {
        return reqSpec
                .body(user)
                .when()
                .post(API_REGISTER_USER);
    }

    @Step("Логин существующего пользователя")
    public Response checkLoginExistingUser(User user) {
        return reqSpec
                .log().ifValidationFails()
                .body(user)
                .when()
                .post(API_LOGIN_USER);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(API_DELETE_USER);
    }
}
