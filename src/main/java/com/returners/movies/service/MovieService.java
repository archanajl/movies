package com.returners.movies.service;

import com.returners.movies.exception.MovieIdNotFoundException;
import com.returners.movies.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie addMovie(Movie movie);

    void deleteMovie(Long movieId) throws MovieIdNotFoundException;
}
