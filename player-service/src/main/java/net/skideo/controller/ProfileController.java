package net.skideo.controller;


import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.dto.base.SkideoDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.repository.PlayerRepository;
import net.skideo.service.player.PlayerService;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.RoleFootball;
import net.skideo.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

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
    public SkideoDto<TokenDto> updateLoginAndPassword(@RequestBody AuthDto authDto) {
        playerService.updateLoginAndPassword(authDto);

        return new SkideoDto<TokenDto>(new TokenDto(feignClient.generateToken(authDto.getLogin(),authDto.getPassword(),clientId,clientSecret,"password").getBody()));
    }

    @GetMapping("/roleFootball")
    public SkideoListDto<RoleFootballDto> getRoleFootball() {
        RoleFootball[] values = RoleFootball.values();
        return new SkideoListDto<RoleFootballDto>(Arrays.stream(values).map(rl -> new RoleFootballDto(rl)).collect(Collectors.toList()),0,0,0,0);
    }

}