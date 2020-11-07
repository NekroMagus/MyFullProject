package net.skideo.controller;


import lombok.RequiredArgsConstructor;
import net.skideo.dto.UserDto;
import net.skideo.dto.projections.UserProfileProjection;
import net.skideo.model.enums.RoleFootball;
import net.skideo.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public UserProfileProjection getProfile() {
        return userService.getProfile();
    }

    @PutMapping("/profile")
    public UserDto editProfile(@Valid @RequestBody UserDto newUser) {
        return new UserDto(userService.editUser(newUser));
    }

    @GetMapping("/roleFootball")
    public RoleFootball[] getRoleFootball() {
        return RoleFootball.values();
    }

}