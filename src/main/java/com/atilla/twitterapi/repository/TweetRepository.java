package com.atilla.twitterapi.repository;

import com.atilla.twitterapi.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByUserId(Long userId);
}
