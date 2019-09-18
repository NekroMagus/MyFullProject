package com.football.manager.controller;

import com.football.manager.model.User;
import com.football.manager.service.UserService;
import com.football.manager.service.impl.UserServiceImpl;
import com.football.manager.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for registration page.
 *
 * @author Igor Fliginkikh
 * @version 1.0
 */

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User userForm) {
        User user = new User(userForm.getUsername(),userForm.getPassword());
        userService.saveUser(user);
        return "redirect:/";
    }
}