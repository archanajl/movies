package com.returners.movies.exception;

public class NoSuchUserExistsException extends Exception {
    public NoSuchUserExistsException(String errorMessage) {
        super(errorMessage);
    }
}
