package com.football.manager.controller;

import com.football.manager.domain.User;
import com.football.manager.service.UserService;
import com.football.manager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for registration page.
 *
 * @author Igor Fliginkikh
 * @version 1.01
 */

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User(username, password);
        userService.saveUser(user);
        return "redirect:/";
    }
}
