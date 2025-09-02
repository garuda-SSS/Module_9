package ru.cinescope.api.steps;

import io.qameta.allure.Step;
import ru.cinescope.api.clients.MovieClient;
import ru.cinescope.api.dto.*;

public class MovieSteps {
    private final MovieClient movie = new MovieClient();

    @Step("Ищем фильм по ID")
    public MovieResponse findMovieById(int movieId, AuthResponse user) {
        return movie.findMovieById(movieId, user);
    }

    @Step("Публикуем новый фильм")
    public MovieResponse createMovie(AuthResponse user, MovieRequest newMovie) {
        return movie.createMovie(user, newMovie);
    }

    @Step("Удаляем фильм")
    public void deleteMovie(int movieId, AuthResponse user) {
        movie.deleteMovie(movieId, user);
    }

    @Step("Удаляем комментарий")
    public void deleteComment(int movieId, AuthResponse user) {
        movie.deleteComment(movieId, user);
    }

    @Step("Создаем комментарий")
    public Reviews createComment(int movieId, AuthResponse user, ReviewsForPost comment) {
        return movie.createComment(movieId, user, comment);
    }
}
