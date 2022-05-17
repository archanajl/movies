package com.returners.movies.service;

import com.returners.movies.model.User;
import com.returners.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean deleteUserById(Long id) {
        if(userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            userRepository.delete(user);
            return true;
        }
        else return false;
    }
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

}
