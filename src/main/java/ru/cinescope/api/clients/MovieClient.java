package ru.cinescope.api.clients;

import ru.cinescope.api.dto.*;
import ru.cinescope.api.spec.MovieSpec;

import static io.restassured.RestAssured.given;

public class MovieClient {

    public MovieResponse findMovieById(int movieId, AuthResponse user) {
        return given()
                .spec(MovieSpec.baseRequestSpec())
                .pathParam("id", movieId)
                .header("Authorization", "Bearer " + user.getAccessToken())
                .when()
                .get("/movies/{id}")
                .then()
                .spec(MovieSpec.jsonSuccess())
                .log().all()
                .extract().as(MovieResponse.class);
    }

    public MovieResponse createMovie(AuthResponse user, MovieRequest newMovie) {
        return given()
                .spec(MovieSpec.baseRequestSpec())
                .body(newMovie)
                .header("Authorization", "Bearer " + user.getAccessToken())
                .when()
                .post("/movies")
                .then()
                .spec(MovieSpec.createSuccess())
                .log().all()
                .extract().as(MovieResponse.class);
    }

    public void deleteMovie(int movieId, AuthResponse user) {
        given()
                .spec(MovieSpec.baseRequestSpec())
                .pathParam("id", movieId)
                .header("Authorization", "Bearer " + user.getAccessToken())
                .when()
                .delete("/movies/{id}")
                .then()
                .spec(MovieSpec.jsonSuccess());
    }

    public void deleteComment(int movieId, AuthResponse user) {
        given()
                .spec(MovieSpec.baseRequestSpec())
                .pathParam("id", movieId)
                .header("Authorization", "Bearer " + user.getAccessToken())
                .when()
                .delete("/movies/{id}/reviews")
                .then()
                .spec(MovieSpec.jsonSuccess());
    }

    public Reviews createComment(int movieId, AuthResponse user, ReviewsForPost comment) {
        return given()
                .spec(MovieSpec.baseRequestSpec())
                .body(comment)
                .pathParam("id", movieId)
                .header("Authorization", "Bearer " + user.getAccessToken())
                .when()
                .post("/movies/{id}/reviews")
                .then()
                .spec(MovieSpec.createSuccess())
                .log().all()
                .extract().as(Reviews.class);
    }

}
