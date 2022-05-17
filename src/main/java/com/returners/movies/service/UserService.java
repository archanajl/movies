package com.returners.movies.service;

import com.returners.movies.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findUserById(Long id);
    List<User> getAllUsers();
    boolean deleteUserById(Long id);
}
