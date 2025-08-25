package ru.cinescope.api.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class MovieSpec {
    public static RequestSpecification baseRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.cinescope.t-qa.ru")
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }

    public static ResponseSpecification jsonSuccess() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();
    }

    public static ResponseSpecification createSuccess() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType("application/json")
                .build();
    }
}
