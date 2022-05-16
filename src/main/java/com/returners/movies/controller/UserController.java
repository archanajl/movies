package com.returners.movies.controller;

import com.returners.movies.model.DataResponse;
import com.returners.movies.model.Movie;
import com.returners.movies.model.User;
import com.returners.movies.service.UserService;
import com.returners.movies.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse> getAllUsers(){
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        return ResponseUtil.getSuccessResponse(users,"All users fetched") ;
    }

    @GetMapping({"/{userId}"})
    public ResponseEntity<DataResponse> getUserById(@PathVariable Long userId){
        Optional<User> user = userService.findUserById(userId);
        System.out.println(user);
        return ResponseUtil.getSuccessResponse(user,"All users fetched") ;
    }
}
