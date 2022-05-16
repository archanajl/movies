package com.returners.movies.controller;

import com.returners.movies.model.DataResponse;
import com.returners.movies.model.Movie;
import com.returners.movies.service.MovieService;
import com.returners.movies.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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



}

