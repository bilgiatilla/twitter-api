package com.atilla.twitterapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TweetRequest {
    private String content;
    private Long userId;
}
