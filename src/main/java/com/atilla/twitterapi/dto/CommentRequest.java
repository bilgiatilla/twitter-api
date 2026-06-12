package com.atilla.twitterapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String content;
    private Long userId;
    private Long tweetId;
}
