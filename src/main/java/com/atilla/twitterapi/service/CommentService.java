package com.atilla.twitterapi.service;

import com.atilla.twitterapi.dto.CommentRequest;
import com.atilla.twitterapi.dto.UpdateCommentRequest;
import com.atilla.twitterapi.entity.Comment;
import com.atilla.twitterapi.entity.Tweet;
import com.atilla.twitterapi.entity.User;
import com.atilla.twitterapi.exception.CommentNotFoundException;
import com.atilla.twitterapi.exception.TweetNotFoundException;
import com.atilla.twitterapi.exception.UserNotFoundException;
import com.atilla.twitterapi.repository.CommentRepository;
import com.atilla.twitterapi.repository.TweetRepository;
import com.atilla.twitterapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    public CommentService(
            CommentRepository commentRepository,
            UserRepository userRepository,
            TweetRepository tweetRepository) {

        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public Comment createComment(CommentRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Tweet tweet = tweetRepository.findById(request.getTweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setUser(user);
        comment.setTweet(tweet);

        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, UpdateCommentRequest request) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        comment.setContent(request.getContent());

        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        commentRepository.delete(comment);
    }
}