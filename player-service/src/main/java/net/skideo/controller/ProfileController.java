package net.skideo.controller;


import net.skideo.dto.UserDto;
import net.skideo.dto.UserProfileDto;
import net.skideo.service.player.PlayerService;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.RoleFootball;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class ProfileController {

    private final PlayerService playerService;

    @GetMapping("/profile")
    public UserProfileDto getProfile(@RequestParam(required = false) Long id) {
        if(id==null) {
            return playerService.getProfile(playerService.getIdByLogin(playerService.getLoginCurrentUser()));
        }
        return playerService.getProfile(id);
    }

    @PutMapping("/profile")
    public UserDto editProfile(@RequestBody UserDto newUser) {
        return new UserDto(playerService.editUser(newUser));
    }

    @GetMapping("/roleFootball")
    public RoleFootball[] getRoleFootball() {
        return RoleFootball.values();
    }

}