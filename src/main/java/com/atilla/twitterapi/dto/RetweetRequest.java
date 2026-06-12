package com.atilla.twitterapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetweetRequest {
    private Long userId;
    private Long tweetId;
}
