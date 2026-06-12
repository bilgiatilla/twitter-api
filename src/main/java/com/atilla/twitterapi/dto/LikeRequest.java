package com.atilla.twitterapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeRequest {
    @NotNull(message = "User id is required")
    private Long userId;

    @NotNull(message = "Tweet id is required")
    private Long tweetId;
}
