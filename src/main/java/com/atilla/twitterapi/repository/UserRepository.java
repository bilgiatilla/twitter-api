package com.atilla.twitterapi.repository;

import com.atilla.twitterapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
