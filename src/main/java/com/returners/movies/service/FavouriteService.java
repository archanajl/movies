package com.returners.movies.service;

import com.returners.movies.model.User;

public interface FavouriteService {
   User addFavourite(Long userId, Long movieId);
   User deleteFavourite(Long userId, Long movieId);
}