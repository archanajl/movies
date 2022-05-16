package com.returners.movies.service;

import com.returners.movies.exception.MovieIdNotFound;
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
            Genre genre = entityManager.find(Genre.class,movie.getGenre_id().getId());
            Certification certification = entityManager.find(Certification.class,movie.getCertification_id().getId());
            movie.setGenre_id(genre);
            movie.setCertification_id(certification);
            return movieRepository.save(movie);
        }
    }

    @Override
    public void deleteMovie(Long movieId) throws MovieIdNotFound {
            if (!movieRepository.existsById(movieId)) {
              throw new MovieIdNotFound();
            }
            movieRepository.deleteById(movieId);
    }

}
