package com.atilla.twitterapi.controller;

import com.atilla.twitterapi.entity.User;

import com.atilla.twitterapi.repository.UserRepository;
import com.atilla.twitterapi.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUSer(@RequestBody User user) {
        return userService.createUser(user);
    }
}
