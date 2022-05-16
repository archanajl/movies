package com.returners.movies.controller;

import com.returners.movies.model.User;
import com.returners.movies.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTests {

    @Mock
    private UserServiceImpl mockUserServiceImpl;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvcController;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testDeleteMappingDeleteAUser() throws Exception {
        Long id = 1L;
        User user = new User(1L,"test","test123",25,"test@gmail.com","Mary","0161 496 0636");
        when(mockUserServiceImpl.deleteUserById(id)).thenReturn(true);
        ResultActions response = this.mockMvcController.perform( MockMvcRequestBuilders.delete("/api/v1/user/delete/" + id));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
