package ru.cinescope.api.steps;

import io.qameta.allure.Step;
import ru.cinescope.api.clients.MovieClient;
import ru.cinescope.api.dto.AuthResponse;
import ru.cinescope.api.dto.MovieRequest;
import ru.cinescope.api.dto.MovieResponse;

public class MovieSteps {
    private MovieClient movie = new MovieClient();

    @Step("Ищем фильм по ID")
    public MovieResponse findMovieById(int movieId, AuthResponse user){
        return movie.findMovieById(movieId,user);
    }

    @Step("Публикуем новый фильм")
    public MovieResponse createMovie(AuthResponse user, MovieRequest newMovie){
        return movie.createMovie(user,newMovie);
    }

    @Step("Удаляем фильм")
    public void deleteMovie(int movieId, AuthResponse user){
        movie.deleteMovie(movieId,user);
    }
}
