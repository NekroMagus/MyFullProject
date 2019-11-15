package com.skideo.controller;

import com.skideo.model.User;
import com.skideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindAllController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String get(){
        return "If you have token";
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }
}
