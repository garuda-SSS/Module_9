import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cinescope.api.dto.AuthResponse;
import ru.cinescope.api.dto.MovieRequest;
import ru.cinescope.api.dto.MovieResponse;
import ru.cinescope.api.steps.MovieSteps;
import ru.cinescope.api.steps.UserSteps;
import ru.cinescope.db.domain.Movies;
import ru.cinescope.db.repository.MoviesRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoviesApiTest {

    private static UserSteps userSteps = new UserSteps();
    private static MovieSteps movieSteps = new MovieSteps();
    private static AuthResponse adminUser;
    private static MoviesRepository repository;
    MovieRequest testFilm = MovieRequest.builder()
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
         adminUser = userSteps.userEnter("test-admin@mail.com","KcLMmxkJMjBD1");
         repository = new MoviesRepository();
    }

    @Test
    @DisplayName("Тест поиска фильма по ID")
    public void filmById() {
        MovieResponse film = movieSteps.findMovieById(1,adminUser);
        Movies filmFromDB = repository.findById(1);

        assertEquals(filmFromDB.getName(), film.getName());
        assertEquals(filmFromDB.getPrice(), film.getPrice());
    }

    @Test
    @DisplayName("Тест публикации фильма")
    public void createFilm() {
        MovieResponse filmPublished = movieSteps.createMovie(adminUser,testFilm);
        Movies filmFromDB = repository.findById(filmPublished.getId());


        //Сравнили отправленный фильм и ответ по АПИ
        assertEquals(testFilm.getName(), filmPublished.getName());
        assertEquals(testFilm.getPrice(), filmPublished.getPrice());
        assertEquals(testFilm.getImageUrl(), filmPublished.getImageUrl());
        assertEquals(testFilm.getLocation(), filmPublished.getLocation());
        assertEquals(testFilm.getGenreId(), filmPublished.getGenreId());

        //Сравнили ответ по АПИ и то, что легло в БД
        assertEquals(filmFromDB.getName(), filmPublished.getName());
        assertEquals(filmFromDB.getPrice(), filmPublished.getPrice());
        assertEquals(filmFromDB.getImage_url(), filmPublished.getImageUrl());
        assertEquals(filmFromDB.getLocation(), filmPublished.getLocation());
        assertEquals(filmFromDB.getGenre_id(), filmPublished.getGenreId());


        movieSteps.deleteMovie(filmPublished.getId(),adminUser);
    }
}
