package com.github.skideo.controller;

import com.github.skideo.model.User;
import com.github.skideo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindAllController {

    private final UserService userService;

    public FindAllController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }
}
