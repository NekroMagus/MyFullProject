package com.football.manager.controller;

import com.football.manager.domain.RoleInFootball;
import com.football.manager.domain.User;
import com.football.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
                          @RequestParam("email") String email, @RequestParam("telephoneNumber") String telephoneNumber,
                          @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfBirth,
                          @RequestParam("address") String address,
                          @RequestParam("roleInFootball") RoleInFootball roleInFootball,
                          @RequestParam("login") String login, @RequestParam("password") String password,
                          @RequestParam("socialNetwork") String socialNetwork){
        User user = new User(login,password,email);
        user.setName(name);
        user.setSurname(surname);
        user.setTelephoneNumber(telephoneNumber);
        user.setDateOfBirth(dateOfBirth);
        user.setDateOfRegistration(new Timestamp(new Date().getTime()));
        user.setAddress(address);
        user.setRoleInFootball(roleInFootball);
        user.setSocialNetwork(socialNetwork);
        userService.addUser(user);
        return "redirect:/";
    }

}
