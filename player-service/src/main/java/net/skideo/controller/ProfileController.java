package net.skideo.controller;


import net.skideo.dto.UserDto;
import net.skideo.dto.UserProfileDto;
import net.skideo.dto.projections.UserProfileProjection;
import net.skideo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.RoleFootball;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public UserProfileDto getProfile(@RequestParam(required = false) Long id) {
        if(id==null) {
            return userService.getProfile(userService.getId(userService.getLoginCurrentUser()));
        }
        return userService.getProfile(id);
    }

    @PutMapping("/profile")
    public UserDto editProfile(@RequestBody UserDto newUser) {
        return new UserDto(userService.editUser(newUser));
    }

    @GetMapping("/roleFootball")
    public RoleFootball[] getRoleFootball() {
        return RoleFootball.values();
    }

}