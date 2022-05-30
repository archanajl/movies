package com.returners.movies.controller;

import com.returners.movies.model.DataResponse;
import com.returners.movies.model.User;
import com.returners.movies.service.FavouriteService;
import com.returners.movies.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/favourites")
public class FavouriteController {

    @Autowired
    FavouriteService favouriteService;

    @PutMapping(
            value = "/favourite/user/{userId}/movies/{movieId}")
    public ResponseEntity<DataResponse> setMovieFavourites(@PathVariable Long userId, @PathVariable Long movieId) {
        User favourites = favouriteService.addFavourite(userId,movieId);
        return ResponseUtil.getSuccessResponse(favourites,"Movie added for  " + userId) ;

    }

    @DeleteMapping(
            value = "/favourite/user/{userId}/movies/{movieId}")
    public ResponseEntity<DataResponse> deleteMovieFavourites(@PathVariable Long userId, @PathVariable Long movieId) {
        User favourites = favouriteService.deleteFavourite(userId,movieId);
        return ResponseUtil.getSuccessResponse(favourites,"Movie deleted for  " + userId) ;

    }
}
