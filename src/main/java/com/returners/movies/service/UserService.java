package com.returners.movies.service;

import com.returners.movies.exception.UserAlreadyExistsException;
import com.returners.movies.model.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);
    List<User> getAllUsers();
    boolean deleteUserById(Long id);

    User addUser(User user) throws UserAlreadyExistsException;


}
