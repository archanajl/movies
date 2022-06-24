package com.returners.movies.service;

import com.returners.movies.constants.Constants;
import com.returners.movies.exception.UserAlreadyExistsException;
import com.returners.movies.model.Movie;
import com.returners.movies.model.User;
import com.returners.movies.repository.MovieRepository;
import com.returners.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    Logger log = Logger.getLogger("Movies");

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("User does not exist with username: " + username);
        }else{
            log.info("User found : " + username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role-> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }

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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }



}
