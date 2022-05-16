package com.returners.movies.service;

import com.returners.movies.exception.MovieIdNotFound;
import com.returners.movies.model.Certification;
import com.returners.movies.model.Genre;
import com.returners.movies.model.Movie;
import com.returners.movies.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
        String[] actors = {"Keira Knightley", "Ralph Fiennes", "Dominic Cooper"};
        Movie movie = new Movie(1L, actors, 6, "The Duchess", 2008, new Certification(4L,"12A"), new Genre(9L,"Drama"));
        movies.add(movie);
        actors = new String[]{"Tim Robbins","Morgan Freeman","Bob Gunton"};
        movie= new Movie(2L, actors, 9, "The Shawshank Redemption", 1994, new Certification(8L,"R"), new Genre(3L,"Comedy"));
        movies.add(movie);
        actors = new String[]{"Jack Nicholson", "Morgan Freeman", "Sean Hayes"};
        movie = new Movie(3L, actors, 7, "The Bucket List", 2007, new Certification(4L,"12A"), new Genre(4L,"Horror"));
        movies.add(movie);
        when(mockMovieRepository.findAll()).thenReturn(movies);

        List<Movie> actualResult = movieServiceImpl.getAllMovies();

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

    @Test
    public void testDeleteMovieWhenIdExists() {
        when(mockMovieRepository.existsById(5L)).thenReturn(true);
        movieServiceImpl.deleteMovie(5L);
        verify(mockMovieRepository, times(1)).deleteById(any());
    }

    @Test
    public void testDeleteMovieWhenIdDoesNotExists() {
        when(mockMovieRepository.existsById(5L)).thenReturn(false);
        assertThrows(MovieIdNotFound.class, () -> movieServiceImpl.deleteMovie(5L));
        verify(mockMovieRepository, times(0)).deleteById(any());
    }

}
