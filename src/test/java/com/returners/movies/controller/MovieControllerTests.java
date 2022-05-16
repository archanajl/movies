package com.returners.movies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.returners.movies.model.Certification;
import com.returners.movies.model.Genre;
import com.returners.movies.model.Movie;
import com.returners.movies.model.SearchCriteria;
import com.returners.movies.service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
        String[] actors = {"Keira Knightley", "Ralph Fiennes", "Dominic Cooper"};
        Movie movie = new Movie(1L, actors, 6, "The Duchess", 2008, new Certification(4L,"12A"), new Genre(9L,"Drama"));
        movies.add(movie);
        actors = new String[]{"Tim Robbins","Morgan Freeman","Bob Gunton"};
        movie= new Movie(2L, actors, 9, "The Shawshank Redemption", 1994, new Certification(8L,"R"), new Genre(3L,"Comedy"));
        movies.add(movie);
        actors = new String[]{"Jack Nicholson", "Morgan Freeman", "Sean Hayes"};
        movie = new Movie(3L, actors, 7, "The Bucket List", 2007, new Certification(4L,"12A"), new Genre(4L,"Horror"));
        movies.add(movie);

        when(mockMovieServiceImpl.getAllMovies()).thenReturn(movies);

        this.mockMvcController.perform(
            MockMvcRequestBuilders.get("/api/v1/movies/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value("The Duchess"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].id").value(2))
           .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].title").value("The Shawshank Redemption"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].id").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].title").value("The Bucket List"));
  }

    @Test
    public void testGetMoviesBySearch() throws Exception {

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

        SearchCriteria search = new SearchCriteria();
        search.setId(1L);
        search.setRating(6);
        search.setTitle("The Bucket List");
        search.setCertificationId(4L);

        String jsonString =mapper.writeValueAsString(search);
        when(mockMovieServiceImpl.getMoviesBySearchCriteria(search)).thenReturn(movies);

        System.out.println(jsonString);
        this.mockMvcController.perform(
                MockMvcRequestBuilders.post("/api/v1/movies/search")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
