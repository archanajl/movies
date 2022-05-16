package com.returners.movies.service;

import com.returners.movies.model.Certification;
import com.returners.movies.model.Genre;
import com.returners.movies.model.Movie;
import com.returners.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        System.out.println(movieRepository.findAll());
        return movies;
    }

    @Override
    public Movie addMovie(Movie movie) {
        if(movieRepository.findByTitle(movie.getTitle())!= null) return null;
        else{
            Genre genre = entityManager.find(Genre.class,movie.getGenre().getId());
            Certification certification = entityManager.find(Certification.class,movie.getCertification().getId());
            movie.setGenre(genre);
            movie.setCertification(certification);
            return movieRepository.save(movie);
        }
    }

}
