package com.atilla.twitterapi.controller;

import com.atilla.twitterapi.dto.TweetRequest;
import com.atilla.twitterapi.dto.UpdateTweetRequest;
import com.atilla.twitterapi.entity.Tweet;
import com.atilla.twitterapi.service.TweetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public Tweet createTweet(@RequestBody TweetRequest tweetRequest) {
        return tweetService.createTweet(tweetRequest);
    }

    @GetMapping("/findByUserId")
    public List<Tweet> findByUserId(@RequestParam Long userId) {
        return tweetService.findByUserId(userId);
    }

    @GetMapping("/findById")
    public Tweet findById(@RequestParam Long id) {
        return tweetService.findById(id);
    }

    @PutMapping("/{id}")
    public Tweet updateTweet(@PathVariable Long id, @RequestBody UpdateTweetRequest request) {
        return tweetService.updateTweet(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteTweet(@PathVariable Long id) {
        tweetService.deleteTweet(id);
        return "Tweet deleted";
    }
}
