package com.returners.movies.controller;

import com.returners.movies.exception.NoSuchUserExistsException;
import com.returners.movies.model.DataResponse;
import com.returners.movies.service.UserService;
import com.returners.movies.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @DeleteMapping({"/delete/{userId}"})
    public ResponseEntity<DataResponse> deleteUserById(@PathVariable("userId") Long userId) throws NoSuchUserExistsException {
        if(userService.deleteUserById(userId)) return ResponseUtil.getSuccessResponse(null, "User " + userId + " is deleted!");
        else throw new NoSuchUserExistsException("Sorry User "+ userId +" does not exist and therefore cannot be deleted!!");
    }
}
