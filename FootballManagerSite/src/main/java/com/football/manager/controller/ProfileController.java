package com.football.manager.controller;

import com.football.manager.model.User;
import com.football.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for profile page.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/edit")
    public String getProfileEdit() {
        return "edit";
    }

    @GetMapping
    public String getProfile() {
        return "profile";
    }

    @PostMapping("/edit")
    public String editProfile(@ModelAttribute User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User updUser = userService.findUserByEmail(auth.getName());
        if (user.getName() != null) {
            updUser.setName(user.getName());
        }
        if (user.getSurname() != null) {
            updUser.setSurname(user.getSurname());
        }
        if (user.getRoleInFootball() != null) {
            updUser.setRoleInFootball(user.getRoleInFootball());
        }
        if (user.getTelephoneNumber() != null) {
            updUser.setTelephoneNumber(user.getTelephoneNumber());
        }
        if (user.getDateOfBirth() != null) {
            updUser.setDateOfBirth(user.getDateOfBirth());
        }
        if (user.getCountry() != null) {
            updUser.setCountry(user.getCountry());
        }
        if (user.getSocialNetwork() != null) {
            updUser.setSocialNetwork(user.getSocialNetwork());
        }
        userService.updateUser(updUser);
        return "redirect:/profile/edit";
    }
}
