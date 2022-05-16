package com.returners.movies.repository;

import com.returners.movies.model.Movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testFindAllMovies() {

        List<String> actors = Arrays.asList(new String[]{"Keira Knightley", "Ralph Fiennes", "Dominic Cooper"});
        Movie movie = new Movie(1L, actors, 6, "The Duchess", 2008, 4, 3);
        movieRepository.save(movie);

        Iterable<Movie> movies = movieRepository.findAll();
        assertThat(movies).hasSize(1);

    }

}
