package com.returners.movies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.returners.movies.model.Movie;
import com.returners.movies.service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class MovieControllerTests {

    @Mock
    private MovieServiceImpl mockMovieServiceImpl;

    @InjectMocks
    private MovieController movieController;

    @Autowired
    private MockMvc mockMvcController;


    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(movieController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testGetAllMoviesReturnsMovies() throws Exception {

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

        when(mockMovieServiceImpl.getAllMovies()).thenReturn(movies);

        this.mockMvcController.perform(
            MockMvcRequestBuilders.get("/api/v1/movie/"))
            .andExpect(MockMvcResultMatchers.status().isOk());
//            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
//            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("The Duchess"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
//            .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("The Shawshank Redemption"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
//            .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value("The Bucket List"));
    }

}
