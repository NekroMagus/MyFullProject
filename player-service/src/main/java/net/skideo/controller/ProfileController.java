package net.skideo.controller;


import net.skideo.dto.UserDto;
import net.skideo.dto.projections.UserProfileProjection;
import net.skideo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.RoleFootball;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public UserProfileProjection getProfile(@RequestHeader("Authorization") String token) {
        return userService.getProfile(token);
    }

    @PutMapping("/profile")
    public UserDto editProfile(@RequestHeader("Authorization") String token,@Valid @RequestBody UserDto newUser) {
        return new UserDto(userService.editUser(token,newUser));
    }

    @GetMapping("/roleFootball")
    public RoleFootball[] getRoleFootball() {
        return RoleFootball.values();
    }

}