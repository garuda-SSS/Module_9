import org.junit.jupiter.api.*;
import ru.cinescope.api.dto.AuthResponse;
import ru.cinescope.api.dto.Reviews;
import ru.cinescope.api.dto.ReviewsRequest;
import ru.cinescope.api.steps.MovieSteps;
import ru.cinescope.api.steps.UserSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewsApiTest {

    private final ReviewsRequest comment = ReviewsRequest.builder().rating(rateOfComment).text(textOfComment).build();
    private static final UserSteps userSteps = new UserSteps();
    private static final MovieSteps movieSteps = new MovieSteps();
    private static AuthResponse adminUser;
    private static final String textOfComment = "Енотт";
    private static final int rateOfComment = 5;
    private static final int movieNumber = 56;


    @BeforeAll
    public static void takeAdminUser() {
        adminUser = userSteps.userLogin("test-admin@mail.com", "KcLMmxkJMjBD1");
    }

    @AfterAll
    public static void deleteComment() {
        movieSteps.deleteComment(movieNumber, adminUser);
    }


    @Test
    @Tag("smoke")
    @DisplayName("Создание коммента")
    public void createComment() {
        Reviews postComment = movieSteps.createComment(movieNumber, adminUser, comment);
        assertEquals(textOfComment, postComment.getText());
        assertEquals(rateOfComment, postComment.getRating());
    }
}
