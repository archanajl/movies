package com.returners.movies.controller;

import com.returners.movies.model.DataResponse;
import com.returners.movies.model.Movie;
import com.returners.movies.model.SearchCriteria;
import com.returners.movies.service.MovieService;
import com.returners.movies.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<DataResponse> getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return ResponseUtil.getSuccessResponse(movies,"All movies fetched") ;
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

    @GetMapping({"/search/{actor}"})
    public ResponseEntity<DataResponse> getMoviesByActor(@PathVariable String actor){
        List<Movie> movies = movieService.getMoviesByActors(actor);
        return ResponseUtil.getSuccessResponse(movies,"All movies fetched") ;
    }

}

