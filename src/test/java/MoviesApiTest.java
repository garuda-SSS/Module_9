import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import ru.cinescope.api.dto.*;
import ru.cinescope.api.steps.MovieSteps;
import ru.cinescope.api.steps.UserSteps;
import ru.cinescope.db.domain.Movies;
import ru.cinescope.db.repository.MoviesRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoviesApiTest {

    private static final UserSteps userSteps = new UserSteps();
    private static final MovieSteps movieSteps = new MovieSteps();
    private static AuthResponse adminUser;
    private static MovieResponse lastCreatedFilm;
    private static MoviesRepository repository;
    private final MovieRequest testFilm = MovieRequest.builder()
            .name("Тестовый5")
            .imageUrl("https://imgur.com/a/yqbh2nE")
            .price(100)
            .description("Это тестовый фильм")
            .location("SPB")
            .published(true)
            .genreId(3)
            .build();

    @BeforeAll
    public static void takeAdminUser() {
        adminUser = userSteps.userLogin("test-admin@mail.com", "KcLMmxkJMjBD1");
        repository = new MoviesRepository();
    }

    @AfterAll
    public static void closeFabric() {
        repository.close();
    }

    @AfterEach
    public void cleanup() {
        if (lastCreatedFilm != null) {
            try {
                movieSteps.deleteMovie(lastCreatedFilm.getId(), adminUser);
                lastCreatedFilm = null;
            } catch (Exception e) {
                // Логируем ошибку, но не падаем
                System.out.println("Ошибка при очистке: " + e.getMessage());
            }
        }
    }

    @Test
    @Tags({
            @Tag("smoke"),
            @Tag("api")
    })
    @DisplayName("Тест поиска фильма по ID")
    public void filmById() {
        MovieResponse film = movieSteps.findMovieById(1, adminUser);
        Movies filmFromDB = repository.findById(1);

        assertEquals(filmFromDB.getName(), film.getName());
        assertEquals(filmFromDB.getPrice(), film.getPrice());
    }


    @Test
    @Tags({
            @Tag("smoke"),
            @Tag("api")
    })
    @DisplayName("Тест публикации фильма")
    public void createFilm() {

        lastCreatedFilm = movieSteps.createMovie(adminUser, testFilm);
        Movies filmFromDB = repository.findById(lastCreatedFilm.getId());


        //Сравнили отправленный фильм и ответ по АПИ
        Allure.step("Сравнили переданный фильм и фильм из ответа сервера", () -> {
            assertEquals(testFilm.getName(), lastCreatedFilm.getName());
            assertEquals(testFilm.getPrice(), lastCreatedFilm.getPrice());
            assertEquals(testFilm.getImageUrl(), lastCreatedFilm.getImageUrl());
            assertEquals(testFilm.getLocation(), lastCreatedFilm.getLocation());
            assertEquals(testFilm.getGenreId(), lastCreatedFilm.getGenreId());
        });
        //Сравнили ответ по АПИ и то, что легло в БД
        Allure.step("Проверили, что в БД создалась запись с верными данными", () -> {
            assertEquals(filmFromDB.getName(), lastCreatedFilm.getName());
            assertEquals(filmFromDB.getPrice(), lastCreatedFilm.getPrice());
            assertEquals(filmFromDB.getImageUrl(), lastCreatedFilm.getImageUrl());
            assertEquals(filmFromDB.getLocation(), lastCreatedFilm.getLocation());
            assertEquals(filmFromDB.getGenreId(), lastCreatedFilm.getGenreId());
        });
    }
}
