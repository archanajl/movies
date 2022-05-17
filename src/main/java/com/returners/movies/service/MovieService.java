package com.returners.movies.service;

import com.returners.movies.exception.MovieIdNotFound;
import com.returners.movies.model.Movie;
import com.returners.movies.model.SearchCriteria;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie insertMovie(Movie movie);
    List<Movie> getMoviesBySearchCriteria(SearchCriteria search);
    List<Movie> getMoviesForUserBySearchCriteria(Long userId,SearchCriteria search);
    List<Movie> getMoviesByActors(String actor);
    Movie addMovie(Movie movie);

    void deleteMovie(Long movieId) throws MovieIdNotFound;
}
