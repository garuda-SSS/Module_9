import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cinescope.api.dto.AuthRequest;
import ru.cinescope.api.dto.AuthResponse;
import ru.cinescope.api.dto.MovieRequest;
import ru.cinescope.api.dto.MovieResponse;
import ru.cinescope.api.spec.AuthSpec;
import ru.cinescope.api.spec.MovieSpec;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoviesApiTest {

    AuthRequest admin = AuthRequest.builder().email("test-admin@mail.com").password("KcLMmxkJMjBD1").build();
    private static AuthResponse adminUser;
    MovieRequest testFilm = MovieRequest.builder()
            .name("Тестовый5")
            .imageUrl("https://imgur.com/a/yqbh2nE")
            .price(100)
            .description("Это тестовый фильм")
            .location("SPB")
            .published(true)
            .genreId(3)
            .build();

    @BeforeEach
    public void takeAdminUser() {
         adminUser = given()
                .spec(AuthSpec.baseRequestSpec())
                .body(admin)
                .when()
                .post("/login")
                .then()
                .log().all()
                .extract()
                .as(AuthResponse.class);
    }

    @Test
    public void filmById() {
        MovieResponse film = given()
                .spec(MovieSpec.baseRequestSpec())
                .pathParam("id",1)
                .header("Authorization", "Bearer " + adminUser.getAccessToken())
                .when()
                .get("/movies/{id}")
                .then()
                .spec(MovieSpec.jsonSuccess())
                .log().all()
                .extract().as(MovieResponse.class);

        assertEquals("Экспресс Бобрихино - Попускалово", film.getName());
        assertEquals(345, film.getPrice());
    }

    @Test
    public void createFilm() {
        MovieResponse filmPublished = given()
                .spec(MovieSpec.baseRequestSpec())
                .body(testFilm)
                .header("Authorization", "Bearer " + adminUser.getAccessToken())
                .when()
                .post("/movies")
                .then()
                .spec(MovieSpec.createSuccess())
                .log().all()
                .extract().as(MovieResponse.class);

        assertEquals(testFilm.getName(), filmPublished.getName());
        assertEquals(testFilm.getPrice(), filmPublished.getPrice());
        assertEquals(testFilm.getImageUrl(), filmPublished.getImageUrl());
        assertEquals(testFilm.getLocation(), filmPublished.getLocation());
        assertEquals(testFilm.getGenreId(), filmPublished.getGenreId());

        given()
                .spec(MovieSpec.baseRequestSpec())
                .pathParam("id",filmPublished.getId())
                .header("Authorization", "Bearer " + adminUser.getAccessToken())
                .when()
                .delete("/movies/{id}")
                .then()
                .spec(MovieSpec.jsonSuccess());
    }
}
