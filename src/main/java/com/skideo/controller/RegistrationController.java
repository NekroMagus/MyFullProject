package com.skideo.controller;

import com.skideo.model.User;
import com.skideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody User user){
        if(userService.findByLogin(user.getLogin()) != null) {
            throw new RuntimeException("User with login " +user.getLogin()+ " is already exists");
        }
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }
}
