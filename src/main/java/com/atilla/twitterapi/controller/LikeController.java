package com.atilla.twitterapi.controller;

import com.atilla.twitterapi.dto.LikeRequest;
import com.atilla.twitterapi.entity.TweetLike;
import com.atilla.twitterapi.service.LikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/like")
    public TweetLike likeTweet(@RequestBody LikeRequest request) {
        return likeService.likeTweet(request);
    }

    @PostMapping("/dislike")
    public String dislikeTweet(@RequestBody LikeRequest request) {
        likeService.dislikeTweet(request);
        return "Like removed";
    }
}
