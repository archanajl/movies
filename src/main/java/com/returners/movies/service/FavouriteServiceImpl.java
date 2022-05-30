package com.returners.movies.service;

import com.returners.movies.model.Movie;
import com.returners.movies.model.User;
import com.returners.movies.repository.MovieRepository;
import com.returners.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteServiceImpl implements FavouriteService{

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public User addFavourite(Long userId, Long movieId) {

        User user= userRepository.findById(userId).get();
        Movie movie = movieRepository.findById(movieId).get();
        user.addFavourite(movie);
        return userRepository.save(user);
    }

    @Override
    public User deleteFavourite(Long userId, Long movieId) {

        User user= userRepository.findById(userId).get();

        Movie movie = movieRepository.findById(movieId).get();

        user.deleteFavourite(movie);
        return userRepository.save(user);
    }
}
