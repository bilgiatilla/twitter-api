package com.atilla.twitterapi.repository;

import com.atilla.twitterapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
