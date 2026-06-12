package com.atilla.twitterapi.repository;

import com.atilla.twitterapi.entity.TweetLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TweetLikeRepository extends JpaRepository<TweetLike, Long> {

    Optional<TweetLike> findByUserIdAndTweetId(Long userId, Long tweetId);

    boolean existsByUserIdAndTweetId(Long userId, Long tweetId);
}
