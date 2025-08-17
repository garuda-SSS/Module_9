import org.junit.jupiter.api.*;
import ru.cinescope.db.domain.Genres;
import ru.cinescope.db.repository.GenresRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenresRepositoryTest {
    private static GenresRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new GenresRepository();

    }

    @AfterAll
    static void tearDown() {
        repository.close();
    }

    @Test
    void findExistGenreById() {
        Genres genre = repository.findById(1);
        assertNotNull(genre);
        assertEquals("Драма", genre.getName());
    }

    @Test
    void findNotExistGenreById() {
        Genres genre = repository.findById(999);
        assertNull(genre);
    }

    @Test
    void findExistGenreByName() {
        Genres genre = repository.findByName("Фэнтези");
        assertEquals(8, genre.getId());
    }


    @Test
    void findNotExistGenreByName() {
        Genres genre = repository.findByName("Еноты");
        assertNull(genre);
    }

    @Test
    void sizeGenre() {
        List<Genres> all= repository.findAll();
        assertEquals(10, all.size());

    }

}
