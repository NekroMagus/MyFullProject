package net.skideo.controller;


import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.AuthDto;
import net.skideo.dto.UserDto;
import net.skideo.dto.UserProfileDto;
import net.skideo.dto.base.SkideoDto;
import net.skideo.repository.PlayerRepository;
import net.skideo.service.player.PlayerService;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.RoleFootball;
import net.skideo.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class ProfileController {

    private final PlayerService playerService;
    private final AuthServiceFeignClient feignClient;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @GetMapping("/profile/{id}")
    public SkideoDto<UserProfileDto> getProfile(@PathVariable(required = false) Long id) {
        if(id==null) {
            return new SkideoDto<UserProfileDto>(playerService.getProfile(playerService.getIdByLogin(SecurityUtils.getLogin())));
        }
        return new SkideoDto<UserProfileDto>(playerService.getProfile(id));
    }

    @PutMapping("/profile")
    public SkideoDto<UserDto> editProfile(@RequestBody UserDto newUser) {
        return new SkideoDto<UserDto>(new UserDto(playerService.editUser(newUser)));
    }

    @PutMapping("/auth")
    public ResponseEntity<OAuth2AccessToken> updateLoginAndPassword(@RequestBody AuthDto authDto) {
        playerService.updateLoginAndPassword(authDto);

        return feignClient.generateToken(authDto.getLogin(),authDto.getPassword(),clientId,clientSecret,"password");
    }

    @GetMapping("/roleFootball")
    public RoleFootball[] getRoleFootball() {
        return RoleFootball.values();
    }

}