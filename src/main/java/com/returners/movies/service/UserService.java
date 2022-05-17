package com.returners.movies.service;

import com.returners.movies.exception.UserAlreadyExistsException;
import com.returners.movies.model.User;

import java.util.List;

public interface UserService {
    boolean deleteUserById(Long id);

    List<User> getAllUsers();
//check existing username,
// email unique and valid,
// age number,
// phone contains number only and should be valid
    void addUser(User user) throws UserAlreadyExistsException;


}
