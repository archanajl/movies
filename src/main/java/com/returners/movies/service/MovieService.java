package com.returners.movies.service;

import com.returners.movies.exception.MovieIdNotFoundException;
import com.returners.movies.model.Movie;
import com.returners.movies.model.SearchCriteria;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> getMoviesBySearchCriteria(SearchCriteria search);
    List<Movie> getMoviesForUserBySearchCriteria(Long userId,SearchCriteria search);
    Movie addMovie(Movie movie);
    void deleteMovie(Long movieId) throws MovieIdNotFoundException;

    Movie getMoviesById(Long movieId);
}
