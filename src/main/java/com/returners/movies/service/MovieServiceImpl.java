package com.returners.movies.service;

import com.returners.movies.exception.MovieIdNotFoundException;
import com.returners.movies.model.*;
import com.returners.movies.repository.CertificationRepository;
import com.returners.movies.repository.GenreRepository;
import com.returners.movies.model.Certification;
import com.returners.movies.model.Genre;
import com.returners.movies.model.Movie;
import com.returners.movies.repository.MovieRepository;
import com.returners.movies.repository.UserRepository;
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

    @Autowired
    CertificationRepository certificationRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
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

    @Override
    public void deleteMovie(Long movieId) throws MovieIdNotFoundException {
            if (!movieRepository.existsById(movieId)) {
              throw new MovieIdNotFoundException();
            }
            movieRepository.deleteById(movieId);
    }

    @Override
    public List<Movie> getMoviesBySearchCriteria(SearchCriteria search){

        return movieRepository.findByIdOrRatingOrTitleOrYearOrCertificationIdOrGenreId(
                search.getId(),
                search.getRating(),
                search.getTitle(),
                search.getYear(),
                search.getCertificationId(),
                search.getGenreId()
        );

    }

    @Override
    public List<Movie> getMoviesForUserBySearchCriteria(Long userId,SearchCriteria search){

        List<Long> certList = getCertificationList(userId);

        return movieRepository.findBySearchCriteria(
                search.getId(),
                search.getRating(),
                //search.getActors(),
                search.getTitle(),
                search.getYear(),
                certList.toArray(new Long[certList.size()]),
                search.getGenreId()
        );
    }

    @Override
    public List<Movie> getMoviesByActors(String actor) {
        return movieRepository.findByActors(actor);
    }

    public List<Long> getCertificationList(Long userId){
        User user = userRepository.findById(userId).get();
        int userAge = 99;
        if(user != null) {
            userAge = user.getAge();
        }
        ArrayList<String> certNames = new ArrayList<>();

        certNames.add("U");
        certNames.add("12A");
        if (userAge >= 18){
            certNames.add("18");
            certNames.add("R");
            certNames.add("TBC");
        }
        if (userAge >= 15){
            certNames.add("15");
        }
        if (userAge >= 12){
            certNames.add("12");
        }
        if (userAge >= 8){
            certNames.add("PG");
        }

        List<Long> certList = certificationRepository.findByNames(certNames);
        return certList;
    }

}
