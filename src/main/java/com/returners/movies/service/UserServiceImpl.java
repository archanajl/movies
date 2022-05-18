package com.returners.movies.service;

import com.returners.movies.constants.Constants;
import com.returners.movies.exception.UserAlreadyExistsException;
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

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        List<User> usersList = getAllUsers();
        if(usersList.stream().filter(u -> u.getUserName().equals(user.getUserName())).count() > 0) {
            throw new UserAlreadyExistsException(String.format(Constants.USERNAME_ALREADY_EXISTS, user.getUserName()));
        }
        if(usersList.stream().filter(u -> u.getEmail().equals(user.getEmail())).count() > 0) {
            throw new UserAlreadyExistsException(String.format(Constants.USER_EMAIL_ALREADY_EXISTS, user.getEmail()));
        }
        return userRepository.save(user);
    }
}
