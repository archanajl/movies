package com.returners.movies.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.returners.movies.constants.Constants;
import com.returners.movies.controller.UserController;
import com.returners.movies.exception.UserAlreadyExistsException;
import com.returners.movies.model.User;
import com.returners.movies.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUserWhenAddedSuccessfully() throws UserAlreadyExistsException {
        List<User> userList = getUsers();
        User user = getUser("Peter", "peter@gmail.com");
        when(mockUserRepository.findAll()).thenReturn(userList);
        User result = userService.addUser(user);
        verify(mockUserRepository, times(1)).save(user);

    }

    @Test
    public void testAddUserWhenEmailExists() throws UserAlreadyExistsException {
        List<User> userList = getUsers();
        User user = getUser("Peter", "paul@gmail.com");
        when(mockUserRepository.findAll()).thenReturn(userList);
        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(user));
        verify(mockUserRepository, times(0)).save(user);

    }

    @Test
    public void testAddUserWhenUsernameExists() throws UserAlreadyExistsException {
        List<User> userList = getUsers();
        User user = getUser("paul", "peter@gmail.com");
        when(mockUserRepository.findAll()).thenReturn(userList);
        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(user));
        verify(mockUserRepository, times(0)).save(user);

    }

    private List<User> getUsers(){
        List<User> users = new ArrayList<>();
        User user = new User(1L, "paul", "test123",30,"paul@gmail.com", "Paul", "0131 496 0626");
        users.add(user);
        user = new User(2L, "jane", "test123",13,"jane@gmail.com", "Jane", "0161 496 0626");
        users.add(user);
        user = new User(3L, "bridget", "test123",18,"bridget@gmail.com", "Bridget", "0116 496 0626");
        users.add(user);
        return users;
    }

    private User getUser(String userName, String email) {
        return new User(4L, userName, "test123",30, email, "Paul", "0131 496 0626");
    }
}
