package com.atilla.twitterapi.service;

import com.atilla.twitterapi.dto.LikeRequest;
import com.atilla.twitterapi.entity.Tweet;
import com.atilla.twitterapi.entity.TweetLike;
import com.atilla.twitterapi.entity.User;
import com.atilla.twitterapi.exception.LikeAlreadyExistsException;
import com.atilla.twitterapi.exception.LikeNotFoundException;
import com.atilla.twitterapi.exception.TweetNotFoundException;
import com.atilla.twitterapi.exception.UserNotFoundException;
import com.atilla.twitterapi.repository.TweetLikeRepository;
import com.atilla.twitterapi.repository.TweetRepository;
import com.atilla.twitterapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final TweetLikeRepository tweetLikeRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;


    public LikeService(TweetLikeRepository tweetLikeRepository, UserRepository userRepository, TweetRepository tweetRepository) {
        this.tweetLikeRepository = tweetLikeRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public TweetLike likeTweet(LikeRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Tweet tweet = tweetRepository.findById(request.getTweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));

        boolean alreadyLiked = tweetLikeRepository.existsByUserIdAndTweetId(
                request.getUserId(),
                request.getTweetId()
        );

        if (alreadyLiked) {
            throw new LikeAlreadyExistsException("User already liked this tweet");
        }

        TweetLike tweetLike = new TweetLike();
        tweetLike.setUser(user);
        tweetLike.setTweet(tweet);

        return tweetLikeRepository.save(tweetLike);
    }
    public void dislikeTweet(LikeRequest request) {

        TweetLike tweetLike = tweetLikeRepository
                .findByUserIdAndTweetId(request.getUserId(), request.getTweetId())
                .orElseThrow(() -> new LikeNotFoundException("Like not found"));

        tweetLikeRepository.delete(tweetLike);
    }
}
