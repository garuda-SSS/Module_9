package ru.cinescope.api.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class AuthSpec {

        public static RequestSpecification baseRequestSpec() {
            return new RequestSpecBuilder()
                    .setBaseUri("https://auth.cinescope.t-qa.ru")
                    .setContentType("application/json")
                    .setAccept("application/json")
                    .build();
        }
}
