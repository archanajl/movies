package com.returners.movies.exception;

public class MovieAlreadyExistsException extends Exception {
    public MovieAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
