package com.returners.movies.controller;

import com.returners.movies.exception.UserAlreadyExistsException;
import com.returners.movies.model.DataResponse;
import com.returners.movies.model.User;
import com.returners.movies.constants.Constants;
import com.returners.movies.exception.NoSuchUserExistsException;
import com.returners.movies.service.UserService;
import com.returners.movies.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseUtil.getSuccessResponse(users,"All users fetched") ;
    }

    @GetMapping({"/{userId}"})
    public ResponseEntity<DataResponse> getUserById(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        return ResponseUtil.getSuccessResponse(user, "All users fetched");
    }

    @DeleteMapping({"/delete/{userId}"})
    public ResponseEntity<DataResponse> deleteUserById(@PathVariable("userId") Long userId) throws NoSuchUserExistsException {
        if(userService.deleteUserById(userId)) return ResponseUtil.getSuccessResponse(null, String.format(Constants.DELETED_SUCCESSFULLY, userId));
        else throw new NoSuchUserExistsException(String.format(Constants.ID_DOES_NOT_EXISTS, userId));
    }

    @PostMapping("/")
    public ResponseEntity<DataResponse> addUser(@RequestBody @Valid User user) {
        try {
            User newUser = userService.addUser(user);
            return ResponseUtil.getSuccessResponse(newUser, String.format(Constants.USER_ADDED_SUCCESSFULLY, newUser.getId()));
        } catch(UserAlreadyExistsException ex) {
            return ResponseUtil.getErrorResponse(HttpStatus.BAD_REQUEST, user, ex.getMessage());
        }

    }
}
