package com.returners.movies.controller;

import com.returners.movies.constants.Constants;
import com.returners.movies.exception.MovieAlreadyExistsException;
import com.returners.movies.exception.MovieIdNotFoundException;
import com.returners.movies.model.DataResponse;
import com.returners.movies.model.Movie;
import com.returners.movies.model.SearchCriteria;
import com.returners.movies.service.MovieService;
import com.returners.movies.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<DataResponse> getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return ResponseUtil.getSuccessResponse(movies,"All movies fetched") ;
    }

    @GetMapping({"/{movieId}"})
    public ResponseEntity<DataResponse> getMoviesById(@PathVariable Long movieId){
        Movie movie = movieService.getMoviesById(movieId);
        return ResponseUtil.getSuccessResponse(movie,"All movies fetched") ;
    }

    @PostMapping(
            value = "/search")
    public ResponseEntity<DataResponse> getMoviesByCriteria(@RequestBody SearchCriteria search) {
        List<Movie> movies = movieService.getMoviesBySearchCriteria(search);
        return ResponseUtil.getSuccessResponse(movies,"All movies fetched") ;

    }

    @PostMapping(
            value = "/search/{userId}")
    public ResponseEntity<DataResponse> getMoviesByCriteria(@PathVariable Long userId, @RequestBody SearchCriteria search) {
        List<Movie> movies = movieService.getMoviesForUserBySearchCriteria(userId,search);
        return ResponseUtil.getSuccessResponse(movies,"All movies fetched for user " + userId) ;

    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DataResponse> addMovie(@RequestBody @Valid Movie movie){
        try{
            Movie newMovie = movieService.addMovie(movie);
            if(newMovie!=null) return ResponseUtil.getSuccessResponse(newMovie, String.format(Constants.MOVIE_ADDED_SUCCESSFULLY, movie.getTitle()));
            else throw new MovieAlreadyExistsException(String.format(Constants.MOVIE_ALREADY_EXISTS, movie.getTitle()));
        }catch(MovieAlreadyExistsException e){
            return ResponseUtil.getErrorResponse(HttpStatus.BAD_REQUEST, movie, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{movieId}")
    public ResponseEntity<DataResponse> deletemovie(@PathVariable("movieId") Long movieId) {
        try {
            movieService.deleteMovie(movieId);
            return ResponseUtil.getSuccessResponse(null, String.format(Constants.DELETED_SUCCESSFULLY, movieId));
        } catch(MovieIdNotFoundException e) {
            return ResponseUtil.getErrorResponse(HttpStatus.NOT_FOUND, null, String.format(Constants.ID_DOES_NOT_EXISTS, movieId));
        }
    }
}

