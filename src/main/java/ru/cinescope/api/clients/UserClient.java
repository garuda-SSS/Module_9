package ru.cinescope.api.clients;

import ru.cinescope.api.dto.AuthRequest;
import ru.cinescope.api.dto.AuthResponse;
import ru.cinescope.api.spec.AuthSpec;

import static io.restassured.RestAssured.given;

public class UserClient {

    public AuthResponse authorization(String email, String password) {
        return given()
                .spec(AuthSpec.baseRequestSpec())
                .body(AuthRequest.builder().email(email).password(password).build())
                .when()
                .post("/login")
                .then()
                .log().all()
                .extract()
                .as(AuthResponse.class);
    }
}
