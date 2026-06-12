package com.atilla.twitterapi.exception;

public class RetweetAlreadyExistsException extends RuntimeException{
    public RetweetAlreadyExistsException(String message) {
        super(message);
    }
}
