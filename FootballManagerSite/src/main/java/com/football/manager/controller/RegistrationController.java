package com.football.manager.controller;

import com.football.manager.domain.User;
import com.football.manager.domain.role.RoleInFootball;
import com.football.manager.service.UserService;
import com.football.manager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Controller for
 *
 * @author Igor Fliginkikh
 * @version 1.0
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
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("surname") String surname,
                          @RequestParam("email") String email,
                          @RequestParam("telephoneNumber") String telephoneNumber,
                          @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfBirth,
                          @RequestParam("address") String address,
                          @RequestParam("roleInFootball") RoleInFootball roleInFootball,
                          @RequestParam("login") String login,
                          @RequestParam("password") String password,
                          @RequestParam("socialNetwork") String socialNetwork
    ) {

        User user = new User(login, password, email, name, surname, telephoneNumber,
                address, roleInFootball, dateOfBirth, socialNetwork);

        userService.saveUser(user);
        return "redirect:/";
    }
}
