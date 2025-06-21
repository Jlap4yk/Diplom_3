package ru.praktikum;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApi {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    protected RequestSpecification reqSpec;

    public BaseApi() {
        RestAssured.baseURI = BASE_URI;
        this.reqSpec = RestAssured.given()
                .contentType(ContentType.JSON);
    }
}
