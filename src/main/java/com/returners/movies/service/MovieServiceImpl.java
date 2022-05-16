package com.returners.movies.service;

import com.returners.movies.model.Certification;
import com.returners.movies.model.Genre;
import com.returners.movies.model.Movie;
import com.returners.movies.model.SearchCriteria;
import com.returners.movies.repository.CertificationRepository;
import com.returners.movies.repository.GenreRepository;
import com.returners.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CertificationRepository certificationRepository;

    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    @Override
    public Movie insertMovie(Movie movie) {
        return movieRepository.save(movie);

    }

    @Override
    public List<Movie> getMoviesBySearchCriteria(SearchCriteria search){

        Optional<Certification> cert = null;
        Optional<Genre> genre = null;
        if (search.getCertificationId() != null) {
            cert = certificationRepository.findById(search.getCertificationId());
        }
        if (search.getGenreId() != null) {
            genre = genreRepository.findById(search.getGenreId());
        }
        return movieRepository.findByIdOrRatingOrTitleOrYearOrCertificationOrGenre(
                search.getId(),
                search.getRating(),
                search.getTitle(),
                search.getYear(),
                cert,
                genre
        );
    }

}
