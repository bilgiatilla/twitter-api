package com.atilla.twitterapi.exception;

public class LikeAlreadyExistsException extends RuntimeException {

    public LikeAlreadyExistsException(String message) {
        super(message);
    }
}
