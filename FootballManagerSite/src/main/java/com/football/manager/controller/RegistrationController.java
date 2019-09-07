package com.football.manager.controller;

import com.football.manager.domain.User;
import com.football.manager.service.UserService;
import com.football.manager.service.UserServiceImpl;
import com.football.manager.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String addUser(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "registration";
        }
        User user = new User(userForm.getUsername(),userForm.getPassword());
        userService.saveUser(user);
        return "redirect:/";
    }
}