package com.atilla.twitterapi.service;

import com.atilla.twitterapi.dto.TweetRequest;
import com.atilla.twitterapi.dto.UpdateTweetRequest;
import com.atilla.twitterapi.entity.Tweet;
import com.atilla.twitterapi.entity.User;
import com.atilla.twitterapi.exception.TweetNotFoundException;
import com.atilla.twitterapi.exception.UserNotFoundException;
import com.atilla.twitterapi.repository.TweetRepository;
import com.atilla.twitterapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TweetService {
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;


    public TweetService(UserRepository userRepository, TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public Tweet createTweet(@RequestBody TweetRequest tweetRequest) {
        User user = userRepository.findById(tweetRequest.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));

        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequest.getContent());
        tweet.setUser(user);

        return tweetRepository.save(tweet);
    }

    public List<Tweet> findByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }

    public Tweet findById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));
    }
    public Tweet updateTweet(Long id, UpdateTweetRequest request) {
        Tweet tweet = tweetRepository.findById(id).orElseThrow(() -> new TweetNotFoundException("Tweet not found"));
        tweet.setContent(request.getContent());

        return tweetRepository.save(tweet);
    }
    public void deleteTweet(Long id) {

        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));

        tweetRepository.delete(tweet);
    }
}
