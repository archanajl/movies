package com.returners.movies.service;

import com.returners.movies.model.Movie;
import com.returners.movies.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
public class MovieServiceTests {

    @Mock
    private MovieRepository mockMovieRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    @Test
    public void testGetAllMoviesReturnsListOfMovies() {

        List<Movie> movies = new ArrayList<>();
        List<String> actors = Arrays.asList(new String[]{"Keira Knightley", "Ralph Fiennes", "Dominic Cooper"});
        Movie movie = new Movie(1L, actors, 6, "The Duchess", 2008, 4, 9);
        movies.add(movie);
        actors = Arrays.asList(new String[]{"Tim Robbins","Morgan Freeman","Bob Gunton"});
        movie= new Movie(2L, actors, 9, "The Shawshank Redemption", 1994, 8, 9);
        movies.add(movie);
        actors = Arrays.asList(new String[]{"Jack Nicholson","Morgan Freeman","Sean Hayes"});
        movie = new Movie(3L, actors, 7, "The Bucket List", 2007, 4, 3);
        movies.add(movie);

        when(mockMovieRepository.findAll()).thenReturn(movies);

        List<Movie> actualResult = movieServiceImpl.getAllMovies();

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

}
