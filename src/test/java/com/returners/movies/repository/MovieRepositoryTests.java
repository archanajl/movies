package com.returners.movies.repository;

import com.returners.movies.model.Certification;
import com.returners.movies.model.Genre;
import com.returners.movies.model.Movie;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Disabled
    public void testFindAllMovies() {

        String[] actors = {"Keira Knightley", "Ralph Fiennes", "Dominic Cooper"};
        Movie movie = new Movie(1L, actors, 6, "The Duchess", 2008, new Certification(4L,"12A"), new Genre(9L,"Drama"));
        entityManager.persist(movie);
        //movieRepository.save(movie);

        Iterable<Movie> movies = movieRepository.findAll();
        assertThat(movies).hasSize(1);

    }

}
