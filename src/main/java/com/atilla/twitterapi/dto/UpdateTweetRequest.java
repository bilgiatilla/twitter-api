package com.atilla.twitterapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTweetRequest {
    @NotBlank(message = "Tweet content cannot be empty")
    @Size(max = 280, message = "Tweet cannot be longer than 280 characters")
    private String content;
}
