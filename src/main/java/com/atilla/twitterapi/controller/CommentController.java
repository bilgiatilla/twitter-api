package com.atilla.twitterapi.controller;

import com.atilla.twitterapi.dto.CommentRequest;
import com.atilla.twitterapi.dto.UpdateCommentRequest;
import com.atilla.twitterapi.entity.Comment;
import com.atilla.twitterapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment createComment(@Valid @RequestBody CommentRequest request) {
        return commentService.createComment(request);
    }

    @PutMapping("/{id}")
    public Comment updateComment (@PathVariable Long id, @Valid @RequestBody UpdateCommentRequest request) {
        return commentService.updateComment(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id, @RequestParam Long userId) {
        commentService.deleteComment(id, userId);
        return "Comment deleted";
    }
}
