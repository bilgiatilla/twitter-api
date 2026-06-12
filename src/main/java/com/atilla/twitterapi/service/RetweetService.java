package com.atilla.twitterapi.service;

import com.atilla.twitterapi.dto.RetweetRequest;
import com.atilla.twitterapi.entity.Retweet;
import com.atilla.twitterapi.entity.Tweet;
import com.atilla.twitterapi.entity.User;
import com.atilla.twitterapi.exception.RetweetAlreadyExistsException;
import com.atilla.twitterapi.exception.RetweetNotFoundException;
import com.atilla.twitterapi.exception.TweetNotFoundException;
import com.atilla.twitterapi.exception.UserNotFoundException;
import com.atilla.twitterapi.repository.RetweetRepository;
import com.atilla.twitterapi.repository.TweetRepository;
import com.atilla.twitterapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RetweetService {
    private final RetweetRepository retweetRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    public RetweetService(RetweetRepository retweetRepository, UserRepository userRepository, TweetRepository tweetRepository) {
        this.retweetRepository = retweetRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }
    public Retweet createRetweet(RetweetRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Tweet tweet = tweetRepository.findById(request.getTweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));

        boolean alreadyRetweeted = retweetRepository.existsByUserIdAndTweetId(
                request.getUserId(),
                request.getTweetId()
        );

        if (alreadyRetweeted) {
            throw new RetweetAlreadyExistsException("User already retweeted this tweet");
        }

        Retweet retweet = new Retweet();
        retweet.setUser(user);
        retweet.setTweet(tweet);

        return retweetRepository.save(retweet);
    }

    public void deleteRetweet(Long id) {

        Retweet retweet = retweetRepository.findById(id)
                .orElseThrow(() -> new RetweetNotFoundException("Retweet not found"));

        retweetRepository.delete(retweet);
    }
}
