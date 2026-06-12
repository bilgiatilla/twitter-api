package com.atilla.twitterapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "retweets",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "tweet_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Retweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}