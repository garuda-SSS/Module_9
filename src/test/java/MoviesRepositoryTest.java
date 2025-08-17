import org.junit.jupiter.api.*;
import ru.cinescope.db.domain.Movies;
import ru.cinescope.db.repository.MoviesRepository;


import static org.junit.jupiter.api.Assertions.*;

class MoviesRepositoryTest {
    private static MoviesRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new MoviesRepository();

    }

    @AfterAll
    static void tearDown() {
        repository.close();
    }

    @Test
    void findExistMovieById() {
        Movies movie = repository.findById(1);
        assertNotNull(movie);
        assertEquals("Экспресс Бобрихино - Попускалово", movie.getName());
    }

    @Test
    void findNotExistMovieById() {
        Movies movie = repository.findById(99999999);
        assertNull(movie);
    }

    @Test
    void findExistMovieByName() {
        Movies movie = repository.findByName("Леон");
        assertEquals(28, movie.getId());
        assertEquals("Осиротевшая девочка становится" +
                " напарницей наемного убийцы. Культовый " +
                "триллер с Жаном Рено и Натали Портман", movie.getDescription());
    }


    @Test
    void findNotExistMovieByName() {
        Movies movie = repository.findByName(" ");
        assertNull(movie);
    }


    @Test
    void findNotExistGenreByDescription() {
        Movies movie = repository.findByDescription("Молодая пара уезжает из" +
                " своего села в новое село, ибо коров всех съели. " +
                "На их поиски выдвигается председатель сельхоза " +
                "Бобрихино из последнего вагона, а из первого" +
                " вагона наряд ополчения попускаловых вегетарианцев. " +
                "Бестселлер нашего времени из разряда \"Игры на выживание " +
                "или как пройти с бутылкой водки мимо охранника Пятерочки\".");
        assertEquals(1, movie.getId());
        assertEquals("Экспресс Бобрихино - Попускалово", movie.getName());
        assertEquals(345, movie.getPrice());


    }


}
