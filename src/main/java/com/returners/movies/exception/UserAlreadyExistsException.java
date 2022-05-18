package com.returners.movies.exception;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
