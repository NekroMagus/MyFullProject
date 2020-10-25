package com.scout.service.controller;

import com.scout.service.exception.ScoutsAlreadyExistsException;
import com.scout.service.exception.WrongLoginOrPasswordException;
import com.scout.service.security.jwt.JwtProvider;
import com.scout.service.service.AuthorizationService;
import com.scout.service.service.ScoutService;
import data.service.dto.AuthDto;
import data.service.dto.RegDto;
import data.service.dto.TokenDto;
import data.service.model.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthorizationRestController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private ScoutService scoutService;

    @Autowired
    private JwtProvider provider;

    @PostMapping("/authenticate")
    public TokenDto authenticate(@Valid @RequestBody AuthDto authDto) {

        if (!authorizationService.isCorrectPassword(authDto,scoutService.findByLogin(authDto.getLogin()))) {
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }

        return new TokenDto(provider.generateToken(authDto.getLogin()));
    }


    @PostMapping("/registration")
    public TokenDto registration(@Valid @RequestBody RegDto regDto) {
        if (authorizationService.isScoutExists(regDto.getLogin())) {
            throw new ScoutsAlreadyExistsException("User already exists");
        }
        scoutService.save(new Scout(regDto));
        return new TokenDto(provider.generateToken(regDto.getLogin()));
    }

}
