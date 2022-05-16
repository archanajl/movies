package com.returners.movies.controller;

import com.returners.movies.constants.Constants;
import com.returners.movies.exception.NoSuchUserExistsException;
import com.returners.movies.model.DataResponse;
import com.returners.movies.service.UserService;
import com.returners.movies.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @DeleteMapping({"/delete/{userId}"})
    public ResponseEntity<DataResponse> deleteUserById(@PathVariable("userId") Long userId) throws NoSuchUserExistsException {
        if(userService.deleteUserById(userId)) return ResponseUtil.getSuccessResponse(null, String.format(Constants.DELETED_SUCCESSFULLY, userId));
        else throw new NoSuchUserExistsException(String.format(Constants.ID_DOES_NOT_EXISTS, userId));
    }
}
