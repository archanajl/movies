package com.returners.movies.controller;

import com.returners.movies.exception.MovieAlreadyExistsException;
import com.returners.movies.model.DataResponse;
import com.returners.movies.model.Movie;
import com.returners.movies.service.MovieService;
import com.returners.movies.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<DataResponse> getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        System.out.println(movies);
        return ResponseUtil.getSuccessResponse(movies,"All movies fetched") ;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<DataResponse> addMovie(@RequestBody @Valid Movie movie){
        try{
            Movie newMovie = movieService.addMovie(movie);
            if(newMovie!=null) return ResponseUtil.getSuccessResponse(newMovie, "The movie '" + movie.getTitle() + "' is added!");
            else throw new MovieAlreadyExistsException("Movie already exists! Try adding a different movie.");
        }catch(MovieAlreadyExistsException e){
            return ResponseUtil.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,movie,e.getMessage());
        }
    }





}

