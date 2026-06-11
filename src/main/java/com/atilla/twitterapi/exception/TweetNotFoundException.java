package com.atilla.twitterapi.exception;

public class TweetNotFoundException extends RuntimeException{

    public TweetNotFoundException(String message) {
        super(message);
    }
}
