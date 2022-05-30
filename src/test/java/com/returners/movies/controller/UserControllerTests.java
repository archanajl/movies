package com.returners.movies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.returners.movies.constants.Constants;
import com.returners.movies.exception.UserAlreadyExistsException;
import com.returners.movies.model.User;
import com.returners.movies.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTests {

    @Mock
    private UserServiceImpl mockUserServiceImpl;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvcController;


    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(userController).build();
        mapper = new ObjectMapper();
    }

    public List<User> initializeUsers(){
        List<User> users = new ArrayList<>();
        User user = new User(1L, "paul", "test123",30,"paul@gmail.com", "Paul", "0131 496 0626",null);
        users.add(user);
        user = new User(2L, "jane", "test123",13,"jane@gmail.com", "Jane", "0161 496 0626",null);
        users.add(user);
        user = new User(3L, "bridget", "test123",18,"bridget@gmail.com", "Bridget", "0116 496 0626",null);
        users.add(user);
        return users;
    }

    @Test
    public void testGetAllUsersReturnsUsers() throws Exception {

        List<User> users = initializeUsers();
        when(mockUserServiceImpl.getAllUsers()).thenReturn(users);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/users/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("Paul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].name").value("Bridget"));
    }

    @Test
    public void testGetUsersBySearch() throws Exception {


        User user = new User(1L, "paul", "test123",30,"paul@gmail.com", "Paul", "0131 496 0626",null);

        when(mockUserServiceImpl.findUserById(user.getId())).thenReturn(user);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/users/" + user.getId()))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1L))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Paul"));
    }

    @Test
    public void testDeleteMappingDeleteAUser() throws Exception {
        Long id = 1L;
        User user = new User(1L,"test","test123",25,"test@gmail.com","Mary","0161 496 0636",null);
        when(mockUserServiceImpl.deleteUserById(id)).thenReturn(true);
        ResultActions response = this.mockMvcController.perform( MockMvcRequestBuilders.delete("/users/delete/" + id));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddUserWhenAddedSuccessfully() throws Exception {
        User user = getUser();
        when(mockUserServiceImpl.addUser(user)).thenReturn(user);
        this.mockMvcController.perform(MockMvcRequestBuilders.post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(String.format(Constants.USER_ADDED_SUCCESSFULLY, user.getId())));

        verify(mockUserServiceImpl, times(1)).addUser(user);
    }

    @Test
    public void testAddUserWhenUserAlreadyExists() throws Exception {
        User user = getUser();
        when(mockUserServiceImpl.addUser(user)).thenThrow(new UserAlreadyExistsException(String.format(Constants.USER_EMAIL_ALREADY_EXISTS, user.getEmail())));
        this.mockMvcController.perform(MockMvcRequestBuilders.post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(String.format(Constants.USER_EMAIL_ALREADY_EXISTS, user.getEmail())));

        verify(mockUserServiceImpl, times(1)).addUser(user);
    }

    private User getUser() {
        return new User(1L,"test","test123",25,"test@gmail.com","Mary","8685877909",null);
    }

}
