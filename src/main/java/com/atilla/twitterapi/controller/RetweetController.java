package com.atilla.twitterapi.controller;

import com.atilla.twitterapi.dto.RetweetRequest;
import com.atilla.twitterapi.entity.Retweet;
import com.atilla.twitterapi.service.RetweetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
public class RetweetController {
    private final RetweetService retweetService;

    public RetweetController(RetweetService retweetService) {
        this.retweetService = retweetService;
    }

    @PostMapping
    public Retweet createRetweet(@RequestBody RetweetRequest request) {
        return retweetService.createRetweet(request);
    }

    @DeleteMapping("/{id}")
    public String deleteRetweet(@PathVariable Long id) {
        retweetService.deleteRetweet(id);
        return "Retweet deleted";
    }
}
