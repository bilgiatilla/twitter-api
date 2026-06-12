package com.atilla.twitterapi.exception;

public class RetweetNotFoundException extends RuntimeException {
    public RetweetNotFoundException(String message) {
        super(message);
    }
}
